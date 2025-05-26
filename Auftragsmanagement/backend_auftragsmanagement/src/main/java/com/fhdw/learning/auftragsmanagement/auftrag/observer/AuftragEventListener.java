package com.fhdw.learning.auftragsmanagement.auftrag.observer;

import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuftragEventListener {

    @EventListener
    public void handleAuftragErstellt(AuftragEvent event) {
        Auftrag auftrag = event.getAuftrag();
        int check = event.getCheck();
        switch (check) {
            case 0:
                System.out.println("Aufträge wurden Abgerufen");
                break;
            case 1:
                System.out.println("Auftrag mit der ID " + event.getAuftrag().getAuftragsId() + " abgerufen");
                break;
            case 2:
                System.out.println("Neuer Auftrag erstellt!");
                break;
            case 3:
                System.out.println("Auftrag mit der ID " + event.getAuftrag().getAuftragsId() + " wurde gelöscht");
                break;
            case 4:
                System.out.println("Auftrag mit der ID " + event.getAuftrag().getAuftragsId() + " wurde bearbeitet");
                break;
            default:
                System.out.println("Unbekannte Aktion");
                break;
        }
    }
}
