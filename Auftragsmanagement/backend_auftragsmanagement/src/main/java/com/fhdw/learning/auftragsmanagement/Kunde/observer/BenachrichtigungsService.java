package com.fhdw.learning.auftragsmanagement.Kunde.observer;

import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Service
public class BenachrichtigungsService implements CustomObserver{





    @Override
    public void update(Kunde kunde, String event) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("["+now.format(formatter)+"] "+ event);
    }
}
