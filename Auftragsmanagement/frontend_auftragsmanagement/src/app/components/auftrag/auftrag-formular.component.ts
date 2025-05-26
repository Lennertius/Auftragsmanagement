import { Component, OnInit } from '@angular/core';
import {
  Artikel,
  Auftrag_mit_Artikel_ohne_id,
  Kunde,
} from '../../interfaces/interfaces.model';
import { KundeService } from '../../services/kunde.service';
import { ArtikelService } from '../../services/artikel.service';
import { MyForDirective } from './my-for.directive';
import { CommonModule } from '@angular/common';
import { ArtikelIterator } from './artikel-iterator';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AuftragService } from '../../services/auftrag.service';
import { LoginService } from '../../services/login.service';
import { NavigateToAuftragCommand } from '../../commands/navigate-to-auftrag';
import { Router } from '@angular/router';

interface artikel_mit_anzahl {
  artikel: Artikel;
  anzahl_artikel: number;
}

@Component({
  selector: 'app-auftrag',
  imports: [MyForDirective, CommonModule, ReactiveFormsModule],
  templateUrl: './auftrag-formular.component.html',
  styleUrl: './auftrag-formular.component.scss',
})
export class AuftragComponent implements OnInit {
  iterator: ArtikelIterator | null = null;
  kunden: Kunde[] = [];
  artikel: Artikel[] = [];
  artikel_mit_anzahl: artikel_mit_anzahl[] = [];
  aktuelleArtikel: Artikel[] = [];
  auftragForm!: FormGroup;

  constructor(
    private kundeService: KundeService,
    private artikelService: ArtikelService,
    private fb: FormBuilder,
    private auftragService: AuftragService,
    private loginService: LoginService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.kundeService.getKunde().subscribe((data) => {
      this.kunden = data;
    });

    this.auftragForm = this.fb.group({
      auftragName: [''],
      beschreibung: [''],
      kundeId: [''],
      erstelldatum: Date
    });

    this.artikelService.getArtikel().subscribe((artikelListe) => {
      this.artikel = artikelListe;
      this.iterator = new ArtikelIterator(this.artikel);
      this.iteriereArtikel();
    });
  }

  iteriereArtikel(): void {
    if (!this.iterator) return;

    this.aktuelleArtikel = [];
    this.iterator.reset();
    while (this.iterator.hasNext()) {
      const artikel = this.iterator.next();
      if (artikel) {
        this.aktuelleArtikel.push(artikel);
      }
    }
  }

  erstellen() {
    const formValues = this.auftragForm.value;
    const kundeId = Number(this.auftragForm.value.kundeId);
    if (kundeId != null) {
      const auftrag: Auftrag_mit_Artikel_ohne_id = {
        name: formValues.auftragName,
        beschreibung: formValues.beschreibung,
        erstelldatum: formValues.erstelldatum,
        kunde: { kundenId: kundeId },
        positionen: this.artikel_mit_anzahl.map((item) => ({
          artikel: { artikelId: item.artikel.artikelId },
          menge: item.anzahl_artikel,
        })),
      };

      this.auftragService.createAuftrag(auftrag).subscribe({
        next: (response) => {
          console.log('Auftrag erfolgreich erstellt:', response);
        },
        error: (error) => {
          console.error('Fehler beim Erstellen des Auftrags:', error);
        },
      });
      const command = new NavigateToAuftragCommand(this.router);
      command.execute();
    }
  }

  hinzufuegenOderErhoehen(artikel: Artikel): void {
    const eintrag = this.artikel_mit_anzahl.find(
      (e) => e.artikel.artikelId === artikel.artikelId
    );
    if (eintrag) {
      eintrag.anzahl_artikel++;
    } else {
      this.artikel_mit_anzahl.push({ artikel, anzahl_artikel: 1 });
    }
  }

  verringern(artikel: Artikel): void {
    const eintrag = this.artikel_mit_anzahl.find(
      (e) => e.artikel.artikelId === artikel.artikelId
    );
    if (eintrag) {
      eintrag.anzahl_artikel--;
      if (eintrag.anzahl_artikel <= 0) {
        this.artikel_mit_anzahl = this.artikel_mit_anzahl.filter(
          (e) => e.artikel.artikelId !== artikel.artikelId
        );
      }
    }
  }

  getAnzahl(artikel: Artikel): number {
    const eintrag = this.artikel_mit_anzahl.find(
      (e) => e.artikel.artikelId === artikel.artikelId
    );
    return eintrag ? eintrag.anzahl_artikel : 0;
  }

  get isUser(): boolean {
    return this.loginService.isUser();
  }
}
