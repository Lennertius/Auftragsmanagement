import { Injectable } from '@angular/core';
import { Artikel, ArtikelFilterStrategy, ArtikelSortierStrategy } from '../../interfaces/interfaces.model';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ArtikelListMediatorService {
  private alleArtikel: Artikel[] = [];
  private filterStrategien: ArtikelFilterStrategy[] = [];
  private sortierStrategie: ArtikelSortierStrategy | null = null;

  private artikelSubject = new BehaviorSubject<Artikel[]>([]);
  filteredAndSorted$ = this.artikelSubject.asObservable();

  setAlleArtikel(artikel: Artikel[]) {
    console.log("Mediator hat Daten erhalten:", artikel);
    this.alleArtikel = artikel;
    this.aktualisieren();
  }

  setFilterStrategien(strategien: ArtikelFilterStrategy[]) {
    this.filterStrategien = strategien;
    this.aktualisieren();
  }

  setSortierStrategie(strategie: ArtikelSortierStrategy) {
    this.sortierStrategie = strategie;
    this.aktualisieren();
  }

  private aktualisieren() {
    console.log("Aktualisieren wird ausgeführt"); 
    let gefiltert = [...this.alleArtikel];

    for (const f of this.filterStrategien) {
      gefiltert = f.filter(gefiltert);
    }

    if (this.sortierStrategie) {
      gefiltert = this.sortierStrategie.sortiere(gefiltert);
    }
    console.log("Ergebnis nach Filter & Sortierung:", gefiltert);
    this.artikelSubject.next(gefiltert);
  }
}