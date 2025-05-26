package com.fhdw.learning.auftragsmanagement.auftrag.state;

import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;

public interface AuftragStatus {
    void bearbeiten(Auftrag auftrag);
    void abgeschlossen(Auftrag auftrag);
    void zuruecksetzen(Auftrag auftrag);
}
