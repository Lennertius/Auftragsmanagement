package com.fhdw.learning.auftragsmanagement.benutzer;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/benutzer")
public class BenutzerController {

    @Autowired
    private BenutzerService benutzerService;

    @PostMapping
    public ResponseEntity<Benutzer> createBenutzer(@Valid @RequestBody BenutzerDTO dto) {
        Benutzer benutzer = benutzerService.createBenutzer(dto);
        return ResponseEntity.ok(benutzer);
    }

    @GetMapping
    public List<Benutzer> getBenutzer() {return benutzerService.getBenutzer();}

    @GetMapping("/{id}")
    public ResponseEntity<Benutzer> getBenutzerById(@PathVariable long id) {
        return benutzerService.getBenutzerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBenutzer(@PathVariable long id) {
        boolean deleted = benutzerService.deleteBenutzer(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Benutzer> updateBenutzer(@PathVariable long id, @Valid @RequestBody BenutzerDTO dto) {
        return benutzerService.updateBenutzer(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
