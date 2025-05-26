package com.fhdw.learning.auftragsmanagement.Kunde;

import com.fhdw.learning.auftragsmanagement.Kunde.observer.BenachrichtigungsService;
import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;
import com.fhdw.learning.auftragsmanagement.auftrag.AuftragIterator;
import com.fhdw.learning.auftragsmanagement.auftrag.AuftragRepository;
import com.fhdw.learning.auftragsmanagement.auftragposition.AuftragPosition;
import com.fhdw.learning.auftragsmanagement.auftragposition.AuftragPositionRepository;
import com.fhdw.learning.auftragsmanagement.notiz.Notiz;
import com.fhdw.learning.auftragsmanagement.notiz.NotizFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class KundeService {

    private KundeRepository kundeRepository;
    private KundeFactory kundeFactory;
    private BenachrichtigungsService benachrichtigungsService;
    private final NotizFactory notizFactory;
    @Autowired
    private AuftragPositionRepository auftragPositionRepository;

    @Autowired
    private AuftragRepository auftragRepository;

    public KundeService(KundeRepository kundeRepository, KundeFactory kundeFactory, BenachrichtigungsService benachrichtigungsService, NotizFactory notizFactory) {
        this.kundeRepository = kundeRepository;
        this.kundeFactory = kundeFactory;
        this.benachrichtigungsService = benachrichtigungsService;
        this.notizFactory = notizFactory;
    }

    public Kunde createKunde(Kunde kunde) {

        Kunde neuerKunde = kundeFactory.createKunde(
            kunde.getVorname(),
            kunde.getNachname(),
            kunde.getAdresse(),
            kunde.getTelefonnummer(),
            kunde.getEmail(),
            kunde.getStatus());
        benachrichtigeObserver(neuerKunde, "Neuer Kundendatensatz erzeugt!");
        return kundeRepository.save(neuerKunde);
    }

    public List<Kunde> getKunde() {
        Kunde kunde = new Kunde();
        benachrichtigeObserver(kunde, "Kundendaten abgefragt!");
        return kundeRepository.findAll();
    }

    public Optional<Kunde> getKundeById(long id) {
        Optional<Kunde> getKunde = kundeRepository.findById(id);
        benachrichtigeObserver(getKunde.get(), "Kunde nach ID gesucht!");
        return getKunde;
    }

    public void deleteKunde(long id) {
        Optional<Kunde> kunde = kundeRepository.findById(id);
        benachrichtigeObserver(kunde.get(), "Kunde gelöscht!");
        kundeRepository.deleteById(id);
    }

    public Kunde updateKunde(long id, Kunde neuerKunde) {
        Kunde vorhandenerKunde = kundeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Kunde mit ID " + id + " nicht gefunden"));

        vorhandenerKunde.setVorname(neuerKunde.getVorname());
        vorhandenerKunde.setNachname(neuerKunde.getNachname());
        vorhandenerKunde.setAdresse(neuerKunde.getAdresse());
        vorhandenerKunde.setTelefonnummer(neuerKunde.getTelefonnummer());
        vorhandenerKunde.setEmail(neuerKunde.getEmail());
        vorhandenerKunde.setStatus(neuerKunde.getStatus());

        updateNotizen(vorhandenerKunde, neuerKunde.getNotizen());
        updateAuftraege(vorhandenerKunde);

        return kundeRepository.save(vorhandenerKunde);
    }




    private void benachrichtigeObserver(Kunde kunde, String event) {
        benachrichtigungsService.update(kunde, event);
    }

    public Kunde createKundeMitNotiz(Kunde kunde) {
        List<Notiz> neueNotizen = new ArrayList<>();
        NotizFactory notizFactory = new NotizFactory();
        if (kunde.getNotizen() != null) {
            for (Notiz roheNotiz : kunde.getNotizen()) {
                Notiz notiz = notizFactory.createNotiz(roheNotiz.getText());
                notiz.setKunde(kunde);
                neueNotizen.add(notiz);
            }
        }

        kunde.setNotizen(neueNotizen);
        return kundeRepository.save(kunde);
    }

    private void updateNotizen(Kunde kunde, List<Notiz> neueNotizen) {

        if(neueNotizen == null) {
            return;
        }

        for(Notiz notiz : neueNotizen) {
            if(notiz.getNotizId() == null) {
                Notiz neueNotiz = notizFactory.createNotiz(notiz.getText());
                neueNotiz.setKunde(kunde);
                kunde.getNotizen().add(neueNotiz);
            }else {
                kunde.getNotizen().stream()
                        .filter(n ->n.getNotizId().equals(notiz.getNotizId()))
                        .findFirst()
                        .ifPresent(n -> n.setText(notiz.getText()));
            }
        }
    }

    private void updateAuftraege(Kunde kunde) {
        List<AuftragPosition> positions = auftragPositionRepository.findByAuftrag_Kunde(kunde);
        Set<Auftrag> betroffeneAuftraege = positions.stream()
                .map(AuftragPosition::getAuftrag)
                .collect(Collectors.toSet());
        List<Auftrag> auftragListe = new ArrayList<>(betroffeneAuftraege);
        AuftragIterator iterator = new AuftragIterator(auftragListe);

        while(iterator.hasNext()) {
            Auftrag auftrag = iterator.next();
            auftrag.setKunde(kunde);
            auftragRepository.save(auftrag);
        }
    }

}
