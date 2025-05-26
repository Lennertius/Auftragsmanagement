import { AuftragsFilterStrategy } from './auftrag-filter-strategy';
import { Auftrag } from '../../interfaces/interfaces.model';

export class FilterNachKundeUndStatus implements AuftragsFilterStrategy {
  constructor(private kundenId: number, private status: string) {}

  filter(auftraege: Auftrag[]): Auftrag[] {
    return auftraege.filter(a =>
      a.kunde.kundenId === this.kundenId && a.status === this.status
    );
  }
}
