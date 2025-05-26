package com.fhdw.learning.auftragsmanagement.Kunde.state;

import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;

public interface KundenStatus {
    void zumKunden(Kunde kunde);
    void zumLangkunde(Kunde kunde);
    void zumInteressent(Kunde kunde);
}
