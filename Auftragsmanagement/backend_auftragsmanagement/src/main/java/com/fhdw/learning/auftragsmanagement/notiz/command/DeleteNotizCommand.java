package com.fhdw.learning.auftragsmanagement.notiz.command;

import com.fhdw.learning.auftragsmanagement.notiz.NotizService;

public class DeleteNotizCommand implements NotizCommand<Void>{

    private final NotizService notizService;
    private final long notizId;

    public DeleteNotizCommand(NotizService notizService, long notizId) {
        this.notizId = notizId;
        this.notizService = notizService;
    }

    @Override
    public Void execute() {
        notizService.deleteNotiz(notizId);
        return null;
    }
}
