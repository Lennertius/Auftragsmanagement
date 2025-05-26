package com.fhdw.learning.auftragsmanagement.Kunde;

import org.springframework.web.bind.annotation.*;

import com.fhdw.learning.auftragsmanagement.Kunde.command.CommandInvoker;
import com.fhdw.learning.auftragsmanagement.Kunde.command.CreateKundeCommand;
import com.fhdw.learning.auftragsmanagement.Kunde.command.DeleteKundeCommand;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/kunde")
public class KundeController {


    private final KundeService kundeService;
    private final KundeFactory kundeFactory;
    private final CommandInvoker commandInvoker;

    public KundeController(KundeService kundeService, KundeFactory kundeFactory, CommandInvoker commandInvoker){
        this.kundeService = kundeService;
        this.kundeFactory = kundeFactory;
        this.commandInvoker = commandInvoker;
    }

    @PostMapping
    public Kunde createKunde(@RequestBody Kunde kunde) {
        return kundeService.createKunde(commandInvoker.run(new CreateKundeCommand(kundeFactory, kunde)));
    }

    @GetMapping
    public List<Kunde> getKunde() {return kundeService.getKunde();}

    @GetMapping("/{id}")
    public Optional<Kunde> getKundeById(@PathVariable long id) {return kundeService.getKundeById(id);}

    @DeleteMapping("/{id}")
    public void deleteKunde(@PathVariable long id) {
        commandInvoker.run(new DeleteKundeCommand(kundeService, id));
    }

    @PutMapping("/{id}")
    public Kunde updateKunde(@PathVariable long id, @RequestBody Kunde neuerKunde) {return kundeService.updateKunde(id, neuerKunde);}

    @PostMapping("/notiz")
    public Kunde createKundeMitNotiz(@RequestBody Kunde kunde) {
        return kundeService.createKundeMitNotiz(kunde);
    }
}
