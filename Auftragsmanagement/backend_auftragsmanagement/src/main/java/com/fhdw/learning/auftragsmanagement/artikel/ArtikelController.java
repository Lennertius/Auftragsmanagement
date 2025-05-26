package com.fhdw.learning.auftragsmanagement.artikel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/artikel")
public class ArtikelController {

    @Autowired
    private ArtikelService artikelService;

    @PostMapping
    public Artikel createArtikel(@Valid @RequestBody ArtikelDTO dto) {
        Artikel artikel = artikelService.createArtikel(dto);
        return artikel;
    }

    @GetMapping
    public List<Artikel> getArtikel() {
        return artikelService.getArtikel();
    }

    @GetMapping("/{id}")
    public Optional<Artikel> getArtikelById(@PathVariable Long id) {
        return artikelService.getArtikelById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteArtikel(@PathVariable long id) {artikelService.deleteArtikel(id);}

    @PutMapping("/{id}")
    public Optional<Artikel> updateArtikel(@PathVariable long id, @Valid @RequestBody ArtikelDTO dto) {
        return artikelService.updateArtikel(id, dto);
        
    }
}