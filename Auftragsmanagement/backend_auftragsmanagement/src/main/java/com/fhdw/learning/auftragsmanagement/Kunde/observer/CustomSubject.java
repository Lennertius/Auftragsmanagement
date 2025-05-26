package com.fhdw.learning.auftragsmanagement.Kunde.observer;

public interface CustomSubject {
    void registrieren(CustomObserver observer);
    void entfernen(CustomObserver observer);
    void benachrichtigen();
}
