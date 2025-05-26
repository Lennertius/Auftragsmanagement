import { ArtikelSortierStrategy } from "../../interfaces/interfaces.model";
import { Artikel } from "../../interfaces/interfaces.model";

export class ArtikelNeuesteZuerst implements ArtikelSortierStrategy {
  sortiere(artikel: Artikel[]): Artikel[] {
    return artikel.slice().sort((a, b) => b.artikelId - a.artikelId);
  }
}

export class ArtikelÄltesteZuerst implements ArtikelSortierStrategy {
  sortiere(artikel: Artikel[]): Artikel[] {
    return artikel.slice().sort((a, b) => a.artikelId - b.artikelId);
  }
}
