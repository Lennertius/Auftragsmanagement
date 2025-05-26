package com.fhdw.learning.auftragsmanagement.Kunde;

import org.springframework.stereotype.Component;

@Component
public class KundeFactory {

    public Kunde createKunde(String vorname, String nachname, String adresse, Integer telefonnummer, String email, String status) {
        Kunde kunde = new Kunde();
        kunde.setVorname(vorname);
        kunde.setNachname(nachname);
        kunde.setAdresse(adresse);
        kunde.setTelefonnummer(telefonnummer);
        kunde.setEmail(email);
        kunde.setStatus(status);
        System.out.println("Kunde erstellt über Factory: " + kunde.getVorname() + " " + kunde.getNachname());

        return kunde;
    }
}
