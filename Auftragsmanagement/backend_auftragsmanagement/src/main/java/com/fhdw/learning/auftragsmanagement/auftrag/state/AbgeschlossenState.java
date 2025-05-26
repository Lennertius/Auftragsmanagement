package com.fhdw.learning.auftragsmanagement.auftrag.state;

import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;

public class AbgeschlossenState implements AuftragStatus {

    @Override
    public void bearbeiten(Auftrag auftrag) {
        auftrag.setStatus("in_bearbeitung");
        auftrag.setAuftragStatus(new InBearbeitungState());
    }

    @Override
    public void abgeschlossen(Auftrag auftrag) {
        throw new IllegalStateException("Der Auftrag ist bereits abgeschlossen.");
    }

    @Override
    public void zuruecksetzen(Auftrag auftrag) {
        auftrag.setStatus("neu");
        auftrag.setAuftragStatus(new NeuState());
    }
}
