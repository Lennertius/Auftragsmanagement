import { Artikel } from "../../interfaces/interfaces.model";

export class ArtikelIterator {
  private position = 0;

  constructor(private artikelListe: Artikel[]) {}

  public hasNext(): boolean {
    return this.position < this.artikelListe.length;
  }

  public next(): Artikel | null {
    if (this.hasNext()) {
      return this.artikelListe[this.position++];
    }
    return null;
  }

  public reset(): void {
    this.position = 0;
  }
}
