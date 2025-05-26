import { AuftragsFilterStrategy } from './auftrag-filter-strategy';
import { Auftrag } from '../../interfaces/interfaces.model'; 

export class FilterNachKunde implements AuftragsFilterStrategy {
  constructor(private kundenId: number) {}

  filter(auftraege: Auftrag[]): Auftrag[] {
    return auftraege.filter(a => a.kunde.kundenId === this.kundenId);
  }
}
