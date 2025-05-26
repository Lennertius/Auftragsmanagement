package com.fhdw.learning.auftragsmanagement.rabatt;

import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;
import com.fhdw.learning.auftragsmanagement.auftragposition.AuftragPosition;
import org.springframework.stereotype.Component;

@Component
public class MengenRabatt implements RabattStrategy{

    @Override
    public double applyRabatt(Auftrag auftrag, double gesamtPreis) {
        int anzahl = auftrag.getPositionen().stream().mapToInt(AuftragPosition::getMenge).sum();

        if(anzahl < 10) {
            return gesamtPreis;
        } else if (anzahl < 25) {
            return gesamtPreis * 0.95;
        }else if (anzahl < 50){
            return gesamtPreis * 0.90;
        }else{
            return gesamtPreis * 0.85;
        }
    }
}
