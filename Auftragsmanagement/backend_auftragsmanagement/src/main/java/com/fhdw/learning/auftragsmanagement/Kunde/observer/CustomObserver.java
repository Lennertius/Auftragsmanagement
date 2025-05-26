package com.fhdw.learning.auftragsmanagement.Kunde.observer;

import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;

public interface CustomObserver {
    void update(Kunde kunde, String event);
}
