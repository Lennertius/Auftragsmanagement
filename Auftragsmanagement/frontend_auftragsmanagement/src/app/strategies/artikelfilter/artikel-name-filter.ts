import { ArtikelFilterStrategy } from "../../interfaces/interfaces.model";
import { Artikel } from "../../interfaces/interfaces.model";


export class ArtikelNameFilter implements ArtikelFilterStrategy {
  constructor(private suchbegriff: string) {}

  filter(artikel: Artikel[]): Artikel[] {
    return artikel.filter(a =>
      a.name.toLowerCase().includes(this.suchbegriff.toLowerCase())
    );
  }
}
