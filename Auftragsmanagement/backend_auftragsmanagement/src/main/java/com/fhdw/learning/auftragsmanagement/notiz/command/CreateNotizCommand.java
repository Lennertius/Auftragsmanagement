package com.fhdw.learning.auftragsmanagement.notiz.command;

import com.fhdw.learning.auftragsmanagement.notiz.Notiz;
import com.fhdw.learning.auftragsmanagement.notiz.NotizFactory;

public class CreateNotizCommand implements NotizCommand<Notiz>{
    private final Notiz notiz;
    private final NotizFactory notizFactory;

    public CreateNotizCommand(Notiz notiz, NotizFactory notizFactory) {
        this.notiz = notiz;
        this.notizFactory = notizFactory;
    }

    @Override
    public Notiz execute() {
        return notizFactory.createNotiz(
                notiz.getText()
        );
    }
}
