import { Artikel } from '../interfaces/interfaces.model';

export interface ArtikelSortStrategy {
  sort(artikel: Artikel[]): Artikel[];
}

export class NewestFirstStrategy implements ArtikelSortStrategy {
  sort(artikel: Artikel[]): Artikel[] {
    return artikel.sort((a, b) => b.artikelId - a.artikelId); // Neueste Artikel zuerst
  }
}
