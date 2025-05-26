package com.fhdw.learning.auftragsmanagement.notiz.command;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class NotizCommandInvoker {
    public <R> R run(NotizCommand<R> command){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("["+now.format(formatter) +"] Command wird ausgeführt: "+ command.getClass().getSimpleName());
        return command.execute();
    }
}
