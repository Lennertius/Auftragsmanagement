package com.fhdw.learning.auftragsmanagement.notiz;

import com.fhdw.learning.auftragsmanagement.notiz.command.NotizCommandInvoker;
import com.fhdw.learning.auftragsmanagement.notiz.command.CreateNotizCommand;
import com.fhdw.learning.auftragsmanagement.notiz.command.DeleteNotizCommand;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notiz")
public class NotizController {

    private final NotizService notizService;
    private final NotizCommandInvoker notizCommandInvoker;
    private final NotizFactory notizFactory;

    public NotizController(NotizService notizService, NotizCommandInvoker notizCommandInvoker, NotizFactory notizFactory) {
        this.notizService = notizService;
        this.notizFactory = notizFactory;
        this.notizCommandInvoker = notizCommandInvoker;
    }

    @PostMapping
    public Notiz createNotiz(@RequestBody Notiz notiz) {
        return notizService.createNotiz(notizCommandInvoker.run(new CreateNotizCommand(notiz, notizFactory)));
    }

    @GetMapping
    public List<Notiz> getNotizen() {
        return notizService.getNotizen();
    }

    @GetMapping("/{id}")
    public Optional<Notiz> getNotizById(@PathVariable Long id) {
        return notizService.getNotizById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteNotiz(@PathVariable long id) {
       notizCommandInvoker.run(new DeleteNotizCommand(notizService, id));
    }

    @PutMapping("/{id}")
    public Notiz updateNotiz(@PathVariable long id, @RequestBody Notiz neueNotiz) {
        return notizService.updateNotiz(id, neueNotiz);
    }

}
