import { Artikel, ArtikelFilterStrategy } from "../../interfaces/interfaces.model";



export class ArtikelPreisFilter implements ArtikelFilterStrategy {
  constructor(private min: number, private max: number) {}

  filter(artikel: Artikel[]): Artikel[] {
    return artikel.filter(a => a.preis >= this.min && a.preis <= this.max);
  }
}