import { Artikel } from "../../interfaces/interfaces.model";
import { ArtikelSortierStrategy } from "../../interfaces/interfaces.model";

export class ArtikelPreisAufsteigend implements ArtikelSortierStrategy {
  sortiere(artikel: Artikel[]): Artikel[] {
    return [...artikel].sort((a, b) => a.preis - b.preis);
  }
}

export class ArtikelPreisAbsteigend implements ArtikelSortierStrategy {
  sortiere(artikel: Artikel[]): Artikel[] {
      return [...artikel].sort((a,b) => b.preis - a.preis);
  }
}