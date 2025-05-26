package com.fhdw.learning.auftragsmanagement.auftrag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auftrag")
public class AuftragController {

    @Autowired
    private AuftragService auftragService;

    @PostMapping
    public Auftrag createAuftrag(@RequestBody Auftrag auftrag) {
        return auftragService.createAuftrag(auftrag);
    }

    @GetMapping
    public List<Auftrag> getAuftrag() {
        return auftragService.getAuftrag();
    }

    @GetMapping("/{id}")
    public Optional<Auftrag> getAuftragById(@PathVariable Long id) {
        return auftragService.getAuftragById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuftrag(@PathVariable Long id) {
        auftragService.deleteAuftrag(id);
    }

    @PutMapping("/{id}")
    public Auftrag updateAuftrag(@PathVariable Long id, @RequestBody Auftrag neuerAuftrag) {
        return auftragService.updateAuftrag(id, neuerAuftrag);
    }
}
