package com.fhdw.learning.auftragsmanagement.auftragposition;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fhdw.learning.auftragsmanagement.artikel.Artikel;
import com.fhdw.learning.auftragsmanagement.auftrag.Auftrag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuftragPosition {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "auftrag_id")
    @JsonBackReference
    private Auftrag auftrag;

    @ManyToOne
    @JoinColumn(name = "artikel_id")
    private Artikel artikel;

    private int menge;

}

