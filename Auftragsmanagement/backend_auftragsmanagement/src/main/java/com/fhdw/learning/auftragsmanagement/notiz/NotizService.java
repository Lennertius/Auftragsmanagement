package com.fhdw.learning.auftragsmanagement.notiz;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NotizService {

    private NotizRepository notizRepository;
    private NotizFactory notizFactory;

    public NotizService(NotizRepository notizRepository, NotizFactory notizFactory) {
        this.notizFactory = notizFactory;
        this.notizRepository = notizRepository;
    }

    public Notiz createNotiz(Notiz notiz) {
        Notiz neueNotiz = notizFactory.createNotiz(
                notiz.getText()
        );
        return notizRepository.save(neueNotiz);
    }

    public List<Notiz> getNotizen() {
        return notizRepository.findAll();
    }

    public Optional<Notiz> getNotizById(Long id) {
        return notizRepository.findById(id);
    }

    public void deleteNotiz(Long id) {
        notizRepository.deleteById(id);
    }

    public Notiz updateNotiz(Long id, Notiz neueNotiz) {
        Optional<Notiz> notiz = getNotizById(id);
        if(notiz.isPresent()) {
            Notiz vorhandeneNotiz = notiz.get();
            vorhandeneNotiz.setText(neueNotiz.getText());
            vorhandeneNotiz.setErstelltUm(neueNotiz.getErstelltUm());
            return createNotiz(vorhandeneNotiz);
        }else{
            throw new NoSuchElementException("Notiz mit der ID " + id + " wurde nicht gefunden");
        }
    }
}
