package com.fhdw.learning.auftragsmanagement.Kunde.command;

import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;
import com.fhdw.learning.auftragsmanagement.Kunde.KundeFactory;

public class CreateKundeCommand implements Command<Kunde> {

    private final KundeFactory kundeFactory;
    private final Kunde kunde;

    public CreateKundeCommand(KundeFactory kundeFactory, Kunde kunde) {
        this.kundeFactory = kundeFactory;
        this.kunde = kunde;
    }

    @Override
    public Kunde execute() {
        return kundeFactory.createKunde(
            kunde.getVorname(),
            kunde.getNachname(),
            kunde.getAdresse(),
            kunde.getTelefonnummer(),
            kunde.getEmail(),
            kunde.getStatus()
        );
    }
}