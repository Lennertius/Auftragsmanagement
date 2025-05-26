package com.fhdw.learning.auftragsmanagement.Kunde.command;

import com.fhdw.learning.auftragsmanagement.Kunde.KundeService;

public class DeleteKundeCommand implements Command<Void> {

    private final KundeService kundeService;
    private final long kundeId;

    public DeleteKundeCommand(KundeService kundeService, long kundeId) {
        this.kundeService = kundeService;
        this.kundeId = kundeId;
    }

    @Override
    public Void execute() {
        kundeService.deleteKunde(kundeId);
        return null;
    }
}
