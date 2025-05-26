package com.fhdw.learning.auftragsmanagement.Kunde.command;

import org.springframework.stereotype.Component;

@Component
public class CommandInvoker {
    public <R> R run(Command<R> command) {

        System.out.println("Command wird ausgeführt: " + command.getClass().getSimpleName());
        return command.execute();
    }
}