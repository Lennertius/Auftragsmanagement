package com.fhdw.learning.auftragsmanagement.Kunde.state;

import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;

public class InteressentState implements KundenStatus {
    @Override
    public void zumKunden(Kunde kunde) {
        kunde.setStatus("kunde");
        kunde.setKundenStatus(new KundeState());
    }

    @Override
    public void zumLangkunde(Kunde kunde) {
        throw new IllegalStateException("Ein Interessent kann nicht direkt Langkunde werden.");
    }

    @Override
    public void zumInteressent(Kunde kunde) {
        throw new IllegalStateException("Bereits Interessent.");
    }
}
