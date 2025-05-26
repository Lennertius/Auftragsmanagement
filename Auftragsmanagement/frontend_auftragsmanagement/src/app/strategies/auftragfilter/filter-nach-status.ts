import { AuftragsFilterStrategy } from './auftrag-filter-strategy';
import { Auftrag } from '../../interfaces/interfaces.model';

export class FilterNachStatus implements AuftragsFilterStrategy {
  constructor(private status: string) {}

  filter(auftraege: Auftrag[]): Auftrag[] {
    return auftraege.filter(a => a.status === this.status);
  }
}
