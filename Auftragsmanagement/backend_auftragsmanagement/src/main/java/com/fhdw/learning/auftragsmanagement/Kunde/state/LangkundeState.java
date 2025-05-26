package com.fhdw.learning.auftragsmanagement.Kunde.state;

import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;

public class LangkundeState implements KundenStatus {
    @Override
    public void zumKunden(Kunde kunde) {
        kunde.setStatus("kunde");
        kunde.setKundenStatus(new KundeState());
    }

    @Override
    public void zumLangkunde(Kunde kunde) {
        throw new IllegalStateException("Bereits Langkunde.");
    }

    @Override
    public void zumInteressent(Kunde kunde) {
        kunde.setStatus("interessent");
        kunde.setKundenStatus(new InteressentState());
    }
}
