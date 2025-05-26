import { Auftrag } from "../../interfaces/interfaces.model";

export interface AuftragsFilterStrategy {
    filter (auftraege: Auftrag[]): Auftrag[];
}