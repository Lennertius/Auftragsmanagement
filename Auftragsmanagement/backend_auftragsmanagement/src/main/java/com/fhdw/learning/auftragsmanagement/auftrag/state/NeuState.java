package com.fhdw.learning.auftragsmanagement.auftrag.state;

import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;

public class NeuState implements AuftragStatus {

    @Override
    public void bearbeiten(Auftrag auftrag) {
        auftrag.setStatus("in_bearbeitung");
        auftrag.setAuftragStatus(new InBearbeitungState());
    }

    @Override
    public void abgeschlossen(Auftrag auftrag) {
        throw new IllegalStateException("Ein neuer Auftrag kann nicht direkt abgeschlossen werden.");
    }

    @Override
    public void zuruecksetzen(Auftrag auftrag) {

    }
}
