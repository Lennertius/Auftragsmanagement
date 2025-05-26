package com.fhdw.learning.auftragsmanagement.auftrag.observer;

import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;
import org.springframework.context.ApplicationEvent;


public class AuftragEvent extends ApplicationEvent {

    private final Auftrag auftrag;
    private int check;

    public AuftragEvent(Object source, Auftrag auftrag, int check) {
        super(source);
        this.auftrag = auftrag;
        this.check = check;
    }

    public int getCheck() {
        return check;
    }

    public Auftrag getAuftrag() {
        return auftrag;
    }
}
