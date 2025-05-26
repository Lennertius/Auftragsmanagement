import { Artikel } from "../../interfaces/interfaces.model";
import { ArtikelSortierStrategy } from "../../interfaces/interfaces.model";

export class NameSortierungAZ implements ArtikelSortierStrategy {
  sortiere(artikel: Artikel[]): Artikel[] {
    return [...artikel].sort((a, b) => a.name.localeCompare(b.name));
  }
}

export class NameSortierungZA implements ArtikelSortierStrategy {
  sortiere(artikel: Artikel[]): Artikel[] {
      return [...artikel].sort((a,b) => b.name.localeCompare(a.name));
  }
} 



