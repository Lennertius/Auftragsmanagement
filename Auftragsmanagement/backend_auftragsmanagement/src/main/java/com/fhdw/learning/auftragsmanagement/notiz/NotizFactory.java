package com.fhdw.learning.auftragsmanagement.notiz;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotizFactory {

    public Notiz createNotiz(String text) {
        Notiz notiz = new Notiz();
        notiz.setText(text);
        notiz.setErstelltUm(LocalDateTime.now());
        return notiz;
    }
}
