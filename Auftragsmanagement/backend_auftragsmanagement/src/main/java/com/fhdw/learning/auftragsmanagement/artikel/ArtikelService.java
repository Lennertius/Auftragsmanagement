package com.fhdw.learning.auftragsmanagement.artikel;

import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;
import com.fhdw.learning.auftragsmanagement.auftrag.AuftragRepository;
import com.fhdw.learning.auftragsmanagement.auftragposition.AuftragPosition;
import com.fhdw.learning.auftragsmanagement.auftragposition.AuftragPositionRepository;
import com.fhdw.learning.auftragsmanagement.rabatt.MengenRabatt;
import com.fhdw.learning.auftragsmanagement.rabatt.RabattStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtikelService {

    @Autowired
    private ArtikelRepository artikelRepository;

    @Autowired
    private AuftragRepository auftragRepository;

    @Autowired
    private AuftragPositionRepository auftragPositionRepository;

    public Artikel createArtikel(ArtikelDTO dto) {
        Artikel.Builder builder = new Artikel.Builder()
        .withName(dto.getName())
        .withBeschreibung(dto.getBeschreibung());

        if(dto.getPreis() != null) {
            builder.withPreis(dto.getPreis());
        }

        return artikelRepository.save(builder.build());
    }

    public List<Artikel> getArtikel() {
        return artikelRepository.findAll();
    }

    public Optional<Artikel> getArtikelById(long id) {
        return artikelRepository.findById(id);
    }

    public void deleteArtikel(long id) {artikelRepository.deleteById(id);}

    public Optional<Artikel> updateArtikel(long id, ArtikelDTO dto) {
        return artikelRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setBeschreibung(dto.getBeschreibung());
            existing.setPreis(dto.getPreis());
            Artikel savedArtikel = artikelRepository.save(existing);
            List<AuftragPosition> positionen = auftragPositionRepository.findByArtikel(savedArtikel);
            Set<Auftrag> betroffeneAuftraege = positionen.stream()
                    .map(AuftragPosition::getAuftrag)
                    .collect(Collectors.toSet());

            RabattStrategy strategy = new MengenRabatt();

            for (Auftrag auftrag : betroffeneAuftraege) {
                double gesamtPreis = auftrag.getPositionen().stream()
                        .mapToDouble(pos -> pos.getArtikel().getPreis() * pos.getMenge())
                        .sum();

                double gesamtNachRabatt = strategy.applyRabatt(auftrag, gesamtPreis);
                auftrag.setGesamtWert(gesamtNachRabatt);
                auftragRepository.save(auftrag);
            }

            return savedArtikel;
        });
    }
}
