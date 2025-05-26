package com.fhdw.learning.auftragsmanagement.auftrag.state;

import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;

public class InBearbeitungState implements AuftragStatus {

    @Override
    public void bearbeiten(Auftrag auftrag) {
        throw new IllegalStateException("Der Auftrag ist bereits in Bearbeitung.");
    }

    @Override
    public void abgeschlossen(Auftrag auftrag) {
        auftrag.setStatus("abgeschlossen");
        auftrag.setAuftragStatus(new AbgeschlossenState());
    }

    @Override
    public void zuruecksetzen(Auftrag auftrag) {
        auftrag.setStatus("neu");
        auftrag.setAuftragStatus(new NeuState());
    }
}
