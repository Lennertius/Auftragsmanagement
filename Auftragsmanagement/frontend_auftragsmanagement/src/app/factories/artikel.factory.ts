
import { Injectable } from '@angular/core';
import { Artikel_ohne_ID } from '../interfaces/interfaces.model'; 

@Injectable({ providedIn: 'root' })
export class ArtikelFactory {
  create(name: string, beschreibung: string, preis: number): Artikel_ohne_ID {
    return {
      name: name.trim(),
      beschreibung: beschreibung.trim(),
      preis: preis > 0 ? preis : 0
    };
  }
}

