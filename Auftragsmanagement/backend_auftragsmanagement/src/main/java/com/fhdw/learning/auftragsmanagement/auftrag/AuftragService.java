package com.fhdw.learning.auftragsmanagement.auftrag;

import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;
import com.fhdw.learning.auftragsmanagement.Kunde.KundeRepository;
import com.fhdw.learning.auftragsmanagement.artikel.Artikel;
import com.fhdw.learning.auftragsmanagement.artikel.ArtikelRepository;
import com.fhdw.learning.auftragsmanagement.auftrag.observer.AuftragEvent;
import com.fhdw.learning.auftragsmanagement.auftragposition.AuftragPosition;
import com.fhdw.learning.auftragsmanagement.rabatt.RabattStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class AuftragService {

    @Autowired
    private AuftragRepository auftragRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ArtikelRepository artikelRepository;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private RabattStrategy rabattStrategy;

    @Transactional
    public Auftrag createAuftrag(Auftrag auftrag) {
        Long kundenId = auftrag.getKunde().getKundenId();
        Kunde kunde = kundeRepository.findById(kundenId)
                .orElseThrow(() -> new RuntimeException("Kunde mit ID " + kundenId + " nicht gefunden"));
        auftrag.setKunde(kunde);

        List<AuftragPosition> auftragPositionen = new ArrayList<>();
        double gesamtPreis = 0;
        int gesamtMenge = 0;
        for (AuftragPosition pos : auftrag.getPositionen()) {
            Artikel artikel = artikelRepository.findById(pos.getArtikel().getArtikelId())
                    .orElseThrow(() -> new RuntimeException("Artikel mit ID " + pos.getArtikel().getArtikelId() + " nicht gefunden"));
            pos.setArtikel(artikel);
            pos.setAuftrag(auftrag);
            auftragPositionen.add(pos);
            gesamtPreis += artikel.getPreis() * pos.getMenge();
            gesamtMenge += pos.getMenge();
        }
        auftrag.setGesamtMenge(gesamtMenge);
        auftrag.setPositionen(auftragPositionen);
        double gesamtNachRabatt = rabattStrategy.applyRabatt(auftrag, gesamtPreis);
        auftrag.setGesamtWert(gesamtNachRabatt);

        Auftrag saved = auftragRepository.save(auftrag);
        publisher.publishEvent(new AuftragEvent(this, saved, 2));
        return saved;
    }

    public List<Auftrag> getAuftrag() {
        List<Auftrag> auftragList = auftragRepository.findAll();
        Auftrag leererAuftrag = new Auftrag();
        publisher.publishEvent(new AuftragEvent(this, leererAuftrag, 0));
        return auftragList;
    }

    public Optional<Auftrag> getAuftragById(Long id) {
        Optional<Auftrag> auftrag = auftragRepository.findById(id);
        publisher.publishEvent(new AuftragEvent(this, auftrag.get(), 1));
        return auftrag;
    }
    @Transactional
    public void deleteAuftrag(long id) {
        Optional<Auftrag> delAuftrag = auftragRepository.findById(id);
        auftragRepository.deleteByAuftragId(id);
        auftragRepository.deleteById(id);
        publisher.publishEvent(new AuftragEvent(this, delAuftrag.get(), 3));
    }

    public Auftrag updateAuftrag(long id, Auftrag neuerAuftrag) {
        Optional<Auftrag> auftragOpt = getAuftragById(id);
        if (auftragOpt.isEmpty()) {
            throw new NoSuchElementException("Auftrag mit der ID " + id + " wurde nicht gefunden");
        }

        Auftrag vorhandenerAuftrag = auftragOpt.get();
        vorhandenerAuftrag.setName(neuerAuftrag.getName());
        vorhandenerAuftrag.setBeschreibung(neuerAuftrag.getBeschreibung());
        vorhandenerAuftrag.setKunde(neuerAuftrag.getKunde());

        String neuerStatus = neuerAuftrag.getStatus().toLowerCase();
        String aktuellerStatus = vorhandenerAuftrag.getStatus().toLowerCase();

        if (!neuerStatus.equals(aktuellerStatus)) {
            switch (neuerStatus) {
                case "neu":
                    vorhandenerAuftrag.zuruecksetzen();
                    break;
                case "in_bearbeitung":
                    vorhandenerAuftrag.bearbeiten();
                    break;
                case "abgeschlossen":
                    vorhandenerAuftrag.abschliessen();
                    break;
                default:
                    throw new IllegalArgumentException("Ungültiger Status: " + neuerStatus);
            }
            vorhandenerAuftrag.setStatus(neuerStatus);
        }

        vorhandenerAuftrag.getPositionen().clear();
        double gesamtPreis = 0;
        for (AuftragPosition neuePosition : neuerAuftrag.getPositionen()) {
            Artikel artikel = artikelRepository.findById(neuePosition.getArtikel().getArtikelId())
                    .orElseThrow(() -> new RuntimeException("Artikel mit ID " + neuePosition.getArtikel().getArtikelId() + " nicht gefunden"));

            neuePosition.setArtikel(artikel);
            neuePosition.setAuftrag(vorhandenerAuftrag);
            vorhandenerAuftrag.getPositionen().add(neuePosition);

            gesamtPreis += artikel.getPreis() * neuePosition.getMenge();
        }
        double gesamtNachRabatt = rabattStrategy.applyRabatt(vorhandenerAuftrag, gesamtPreis);
        vorhandenerAuftrag.setGesamtWert(gesamtNachRabatt);

        publisher.publishEvent(new AuftragEvent(this, vorhandenerAuftrag, 4));
        return auftragRepository.save(vorhandenerAuftrag);
    }
}
