
export interface Artikel {
    artikelId: number;
    name: string;
    beschreibung: string;
    preis: number;
  }


export interface Artikel_ohne_ID {
    name: string;
    beschreibung: string;
    preis: number;
  }

 
export interface ArtikelSortierStrategy {
  sortiere(artikel: Artikel[]): Artikel[];
}

export interface ArtikelFilterStrategy {
  filter(artikel: Artikel[]): Artikel[];
}

export interface Kunde {
    kundenId: number;
    vorname:  string;
    nachname: string;
    adresse: string;
    telefonnummer: number;
    email: string;
    status: string;
}
export interface Kunde_ohne_ID {
    vorname:  string;
    nachname: string;
    adresse: string;
    telefonnummer: number;
    email: string;
    status: string;
}
  
export interface Auftrag_landingpage {
  auftragsId: number;
  name: string;
  status: string;
}

export interface Auftrag {
  auftragsId: number;
  name: string;
  beschreibung: string;
  status: string;
  kunde: Kunde;
}

export interface Auftrag_mit_Artikel {
  auftragsId: number;
  name: string;
  beschreibung: string;
  status: string;
  kunde: Kunde;
  positionen: Artikel_mit_Anzahl[];
}

export interface Auftrag_mit_Artikel_ohne_id {
  name: string;
  beschreibung: string;
  erstelldatum: Date;
  kunde: { kundenId: number};
  positionen: ArtikelPosition[];
}

export interface ArtikelPosition {
  artikel: {artikelId: number};
  menge: number;
}

export interface Artikel_mit_Anzahl {
  artikel: Artikel;
  menge: number;
}