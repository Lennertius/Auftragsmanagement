package com.fhdw.learning.auftragsmanagement.auftrag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;
import com.fhdw.learning.auftragsmanagement.auftrag.state.AuftragStatus;
import com.fhdw.learning.auftragsmanagement.auftrag.state.NeuState;
import com.fhdw.learning.auftragsmanagement.auftrag.state.InBearbeitungState;
import com.fhdw.learning.auftragsmanagement.auftrag.state.AbgeschlossenState;
import com.fhdw.learning.auftragsmanagement.auftragposition.AuftragPosition;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auftrag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auftragsId;

    private double gesamtWert;
    private String name;
    private String beschreibung;
    private Date erstelldatum;

    private int gesamtMenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kunden_id", nullable = false)
    private Kunde kunde;

    private String status = "neu";

    @JsonIgnore
    @Transient
    private AuftragStatus auftragStatus;

    @OneToMany(mappedBy = "auftrag", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AuftragPosition> positionen = new ArrayList<>();

    public AuftragStatus getAuftragStatus() {
        if (auftragStatus == null) {
            switch (status.toLowerCase()) {
                case "neu":
                    auftragStatus = new NeuState();
                    break;
                case "in_bearbeitung":
                    auftragStatus = new InBearbeitungState();
                    break;
                case "abgeschlossen":
                    auftragStatus = new AbgeschlossenState();
                    break;
                default:
                    throw new IllegalStateException("Unbekannter Status: " + status);
            }
        }
        return auftragStatus;
    }

    public void setStatus(String status) {
        this.status = status;
        this.auftragStatus = null;
    }

    public void bearbeiten() {
        getAuftragStatus().bearbeiten(this);
    }

    public void abschliessen() {
        getAuftragStatus().abgeschlossen(this);
    }

    public void zuruecksetzen() {
        getAuftragStatus().zuruecksetzen(this);
    }

}
