package com.fhdw.learning.auftragsmanagement.benutzer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BenutzerDTO {

        @NotBlank(message = "Benutzername darf nicht leer sein")
        private String benutzername;

        @NotBlank(message = "Passwort darf nicht leer sein")
        private String passwort;

        private String kuerzel;
        private String abteilung;

        public BenutzerDTO() {}
}
