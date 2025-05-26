import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ArtikelFactory } from '../../../factories/artikel.factory';
import { Artikel, Artikel_ohne_ID, ArtikelFilterStrategy, ArtikelSortierStrategy } from '../../../interfaces/interfaces.model';
import { ArtikelService } from '../../../services/artikel.service';
import { ArtikelPreisAbsteigend, ArtikelPreisAufsteigend } from '../../../strategies/artikelsorter/artikel_Preis_sorter';
import { NameSortierungAZ, NameSortierungZA } from '../../../strategies/artikelsorter/artikel_Name_sorter';
import { SortierTyp } from '../../../strategies/artikelsorter/sortier-typ.enum';
import { LoginService } from '../../../services/login.service';
import { ArtikelNameFilter } from '../../../strategies/artikelfilter/artikel-name-filter';
import { ArtikelPreisFilter } from '../../../strategies/artikelfilter/artikel-preis-filter';
import { ArtikelNeuesteZuerst, ArtikelÄltesteZuerst } from '../../../strategies/artikelsorter/artikel_id_sorter';
import { ArtikelListMediatorService } from '../../../strategies/artikelfilter/artikel-list-mediator.service';


@Component({
  selector: 'app-artikel-liste',
  imports: [CommonModule, RouterModule, FormsModule ],
  templateUrl: './artikel-liste.component.html',
  styleUrl: './artikel-liste.component.scss'
})
export class ArtikelListeComponent implements OnInit {

artikelListe: Artikel[] = [];
suchbegriff = '';
minPreis = 0;
maxPreis = 999999;
sortierTyp = 'nameAZ';


inp_artikel_bezeichnung_name = "";
inp_artikel_beschreibung_name = "";
inp_artikel_preis_name = "";

neuerArtikel: Artikel_ohne_ID = {
    name: "",
    beschreibung: "",
    preis: 0
};

constructor(private artikelService: ArtikelService, private artikelFactory: ArtikelFactory, private loginService: LoginService, private mediator: ArtikelListMediatorService) {}

ngOnInit(): void {
  
this.mediator.filteredAndSorted$.subscribe(data => {
    this.artikelListe = data;
  });

  this.seitenaufruf();

    this.artikelService.getArtikelErstelltListener().subscribe(() => {
    this.seitenaufruf();

  });
  
}

seitenaufruf(): void {
   this.artikelService.getArtikel().subscribe(data => {
    console.log("Backend-Daten angekommen:", data);
    this.artikelListe = data; 
    this.mediator.setAlleArtikel(data);
    this.anwendenFilter();
    this.anwendenSortierung();
  });
}

artikelErstellen(): void {

  const artikel_factory = this.artikelFactory.create(
    this.neuerArtikel.name,
    this.neuerArtikel.beschreibung,
    this.neuerArtikel.preis
  );

 this.artikelService.createArtikel(artikel_factory).subscribe({
  next: (response) => {
    this.resetForm();
    this.artikelService.artikellisteUpdate();
  }
 })
}

resetForm(){
this.neuerArtikel.name = '';
this.neuerArtikel.beschreibung = '';
this.neuerArtikel.preis = 0;
}


 anwendenFilter() {
const filterStrategien: ArtikelFilterStrategy[] = [];

  if (this.suchbegriff.trim()) {
    filterStrategien.push(new ArtikelNameFilter(this.suchbegriff));
  }

 
  const min = Math.min(this.minPreis, this.maxPreis);
  const max = Math.max(this.minPreis, this.maxPreis);
  filterStrategien.push(new ArtikelPreisFilter(min, max));

  this.mediator.setFilterStrategien(filterStrategien);
  }

 anwendenSortierung() {
    let sortierStrategie: ArtikelSortierStrategy;

    switch (this.sortierTyp) {
      case 'nameAZ': sortierStrategie = new NameSortierungAZ(); break;
      case 'nameZA': sortierStrategie = new NameSortierungZA(); break;
      case 'preisAsc': sortierStrategie = new ArtikelPreisAufsteigend(); break;
      case 'preisDesc': sortierStrategie = new ArtikelPreisAbsteigend(); break;
      case 'neueste': sortierStrategie = new ArtikelNeuesteZuerst(); break;
      case 'älteste': sortierStrategie = new ArtikelÄltesteZuerst(); break;
      default: sortierStrategie = new NameSortierungAZ();
    }
   this.mediator.setSortierStrategie(sortierStrategie);
  }


get isUser(): boolean {
  return this.loginService.isUser();
}


}

  