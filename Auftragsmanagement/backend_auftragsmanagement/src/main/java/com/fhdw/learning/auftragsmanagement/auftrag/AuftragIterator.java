package com.fhdw.learning.auftragsmanagement.auftrag;

import java.util.List;
import java.util.NoSuchElementException;

public class AuftragIterator {
    private final List<Auftrag> auftraege;
    private int aktuellerIndex = 0;

    public AuftragIterator(List<Auftrag> auftraege) {
        this.auftraege = auftraege;
    }

    public boolean hasNext() {
        return aktuellerIndex < auftraege.size();
    }

    public Auftrag next() {
        if(!hasNext()){
            throw new NoSuchElementException("Keine weiteren Aufträge vorhanden!");
        }
        return auftraege.get(aktuellerIndex++);
    }
}
