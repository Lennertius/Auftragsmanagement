package com.fhdw.learning.auftragsmanagement.Kunde.state;

import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;

public class KundeState implements KundenStatus {
    @Override
    public void zumKunden(Kunde kunde) {
        throw new IllegalStateException("Bereits Kunde.");
    }

    @Override
    public void zumLangkunde(Kunde kunde) {
        kunde.setStatus("langkunde");
        kunde.setKundenStatus(new LangkundeState());
    }

    @Override
    public void zumInteressent(Kunde kunde) {
        kunde.setStatus("interessent");
        kunde.setKundenStatus(new InteressentState());
    }
}
