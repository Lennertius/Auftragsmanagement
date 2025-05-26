package com.fhdw.learning.auftragsmanagement.notiz;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notizId;

    private LocalDateTime erstelltUm;
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kunden_id")
    private Kunde kunde;
}
