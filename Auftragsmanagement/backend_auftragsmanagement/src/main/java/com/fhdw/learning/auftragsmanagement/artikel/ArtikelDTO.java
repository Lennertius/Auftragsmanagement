package com.fhdw.learning.auftragsmanagement.artikel;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArtikelDTO {
    @NotBlank(message = "Der Name des Artikels darf nicht leer sein.")
    private String name;
    private String beschreibung;

    @NotBlank(message = "Der Preis der Artikels darf nicht leer sein.")
    private Double preis;
}
