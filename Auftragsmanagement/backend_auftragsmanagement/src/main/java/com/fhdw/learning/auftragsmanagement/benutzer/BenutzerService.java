package com.fhdw.learning.auftragsmanagement.benutzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenutzerService {

    @Autowired
    private BenutzerRepository benutzerRepository;

    public Benutzer createBenutzer(BenutzerDTO dto) {
        Benutzer.Builder builder = new Benutzer.Builder()
                .withBenutzername(dto.getBenutzername())
                .withPasswort(dto.getPasswort());

        if (dto.getKuerzel() != null && !dto.getKuerzel().isEmpty()) {
            builder.withKuerzel(dto.getKuerzel());
        }

        if (dto.getAbteilung() !=null && !dto.getAbteilung().isEmpty()) {
            builder.withAbteilung(dto.getAbteilung());
        }

        return benutzerRepository.save(builder.build());
    }

    public List<Benutzer> getBenutzer() {return benutzerRepository.findAll();}

    public Optional<Benutzer> getBenutzerById(long id) {return benutzerRepository.findById(id);}

    public boolean deleteBenutzer(long id) {
        if (benutzerRepository.existsById(id)){
            benutzerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Benutzer> updateBenutzer(long id, BenutzerDTO dto) {
        return benutzerRepository.findById(id).map(existing -> {
            existing.setBenutzername(dto.getBenutzername());
            existing.setPasswort(dto.getPasswort());
            existing.setKuerzel(dto.getKuerzel());
            existing.setAbteilung(dto.getAbteilung());
            return benutzerRepository.save(existing);
        });
    }
}
