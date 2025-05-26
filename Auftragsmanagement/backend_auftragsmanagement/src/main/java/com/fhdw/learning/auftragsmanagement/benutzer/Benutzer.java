package com.fhdw.learning.auftragsmanagement.benutzer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Benutzer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BenutzerId;

    private String benutzername = "";
    private String passwort = "";
    private String kuerzel = "";
    private String abteilung = "";

    public Benutzer() {}

    private Benutzer(Builder builder) {
        this.benutzername = builder.benutzername;
        this.passwort = builder.passwort;
        this.kuerzel = builder.kuerzel;
        this.abteilung = builder.abteilung;
    }

    public static class Builder {
        private String benutzername;
        private String passwort;
        private String kuerzel;
        private String abteilung;


        public Builder withBenutzername(String name) {
            this.benutzername = name;
            return this;
        }

        public Builder withPasswort(String pass) {
            this.passwort = pass;
            return this;
        }

        public Builder withKuerzel(String kuerzel) {
            this.kuerzel = kuerzel;
            return this;
        }

        public Builder withAbteilung(String abt) {
            this.abteilung = abt;
            return this;
        }

        public Benutzer build() {
            return new Benutzer(this);
        }
    }
}
