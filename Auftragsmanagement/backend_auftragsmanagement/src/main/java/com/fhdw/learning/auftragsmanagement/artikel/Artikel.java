package com.fhdw.learning.auftragsmanagement.artikel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;
import com.fhdw.learning.auftragsmanagement.auftragposition.AuftragPosition;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artikelId;
    private String name;
    private String beschreibung;
    private Double preis;

    @OneToMany(mappedBy = "artikel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<AuftragPosition> auftragPositionen = new HashSet<>();

    private Artikel(Builder builder) {
        this.name = builder.name;
        this.beschreibung = builder.beschreibung;
        this.preis = builder.preis;
    }

    public static class Builder {
        private String name;
        private String beschreibung;
        private double preis;

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withBeschreibung(String beschreibung){
            this.beschreibung = beschreibung;
            return this;
        }

        public Builder withPreis(double preis) {
            this.preis = preis;
            return this;
        }
        
        public Artikel build() {
            return new Artikel(this);
        }
    }
}

