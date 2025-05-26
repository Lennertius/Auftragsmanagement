package com.fhdw.learning.auftragsmanagement.Kunde;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fhdw.learning.auftragsmanagement.Kunde.state.InteressentState;
import com.fhdw.learning.auftragsmanagement.Kunde.state.KundeState;
import com.fhdw.learning.auftragsmanagement.Kunde.state.KundenStatus;
import com.fhdw.learning.auftragsmanagement.Kunde.state.LangkundeState;
import com.fhdw.learning.auftragsmanagement.notiz.Notiz;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Kunde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long KundenId;
    private String vorname = "";
    private String nachname = "";
    private String adresse = "";
    private Integer telefonnummer;
    private String email = "";
    private String status = "Interessent";

    @JsonManagedReference
    @OneToMany(mappedBy = "kunde", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notiz> notizen = new ArrayList<>();

    @Transient
    @JsonIgnore
    private KundenStatus kundenStatus;

    public KundenStatus getKundenStatus() {
        if (kundenStatus == null) {
            switch (status.toLowerCase()) {
                case "interessent":
                    kundenStatus = new InteressentState();
                    break;
                case "kunde":
                    kundenStatus = new KundeState();
                    break;
                case "langkunde":
                    kundenStatus = new LangkundeState();
                    break;
                default:
                    throw new IllegalStateException("Unbekannter Kundenstatus: " + status);
            }
        }
        return kundenStatus;
    }

    public void setStatus(String status) {
        this.status = status.toLowerCase();
        this.kundenStatus = null;
    }

    public void zumKunden() {
        getKundenStatus().zumKunden(this);
    }

    public void zumLangkunde() {
        getKundenStatus().zumLangkunde(this);
    }

    public void zumInteressent() {
        getKundenStatus().zumInteressent(this);
    }
}
