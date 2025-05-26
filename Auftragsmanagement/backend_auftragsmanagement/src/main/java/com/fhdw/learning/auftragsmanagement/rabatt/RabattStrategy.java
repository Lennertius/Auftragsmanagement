package com.fhdw.learning.auftragsmanagement.rabatt;

import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;

public interface RabattStrategy {
    double applyRabatt(Auftrag auftrag, double gesamtPreis);
}
