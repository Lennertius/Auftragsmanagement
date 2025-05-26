
import { Injectable } from '@angular/core';
import { Kunde_ohne_ID } from '../interfaces/interfaces.model'; 

@Injectable({ providedIn: 'root' })
export class KundeFactory {
  create(vorname: string, nachname: string, email: string, adresse: string, telefonnummer: number, status: string): Kunde_ohne_ID {
    return {
        vorname: vorname.trim(),
        nachname: nachname.trim(),
        email: email.trim(),
        adresse: adresse.trim(),
        telefonnummer: telefonnummer > 0 ? telefonnummer: 0,
        status: status.trim() 
    };
  }
}

