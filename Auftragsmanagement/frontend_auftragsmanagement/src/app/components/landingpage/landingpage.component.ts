import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router'; 

import { ArtikelService } from '../../services/artikel.service';
import { Artikel, Auftrag, Auftrag_landingpage } from '../../interfaces/interfaces.model';
import { NewestFirstStrategy, ArtikelSortStrategy } from '../../services/artikel-sort.strategy.service';
import { AuftragService } from '../../services/auftrag.service';

@Component({
  selector: 'app-landingpage',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule 
  ],
  templateUrl: './landingpage.component.html',
  styleUrls: ['./landingpage.component.scss']
})
export class LandingpageComponent implements OnInit {
  artikel: Artikel[] = [];
  auftraege: Auftrag_landingpage[] = [];

  constructor(
    private artikelService: ArtikelService,
    private auftragService: AuftragService
  ) {}

  ngOnInit(): void {
    const strategy: ArtikelSortStrategy = new NewestFirstStrategy();

    this.artikelService.getArtikel().subscribe((data: Artikel[]) => {
      this.artikel = data.length ? strategy.sort(data) : [this.nullArtikel()];
    });

    this.auftragService.getAuftraege().subscribe((data: Auftrag[]) => {
      this.auftraege = data.length ? this.sortiereNeuesteAuftraege(data) : [this.nullAuftrag()];
    });
  }

  sortiereNeuesteAuftraege(auftraege: Auftrag[]): Auftrag_landingpage[] {
  return auftraege.sort((a, b) => b.auftragsId - a.auftragsId);
}


  
  nullArtikel(): Artikel {
    return {
      artikelId: 0,
      name: 'Keine Artikel vorhanden',
      preis: 0,
      beschreibung: '-'
    };
  }

  nullAuftrag(): Auftrag_landingpage {
    return {
      auftragsId: 0,
      name: 'Keine Aufträge vorhanden',
      status: ''
    };
  }
}
