import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Auftrag, Kunde } from '../../../interfaces/interfaces.model';
import { KundeService } from '../../../services/kunde.service';
import { Router } from '@angular/router';
import { AuftragService } from '../../../services/auftrag.service';
import { AuftragsFilterStrategy } from '../../../strategies/auftragfilter/auftrag-filter-strategy';
import { FilterNachKunde } from '../../../strategies/auftragfilter/filter-nach-kunde';
import { FilterNachStatus } from '../../../strategies/auftragfilter/filter-nach-status';
import { FilterNachKundeUndStatus } from '../../../strategies/auftragfilter/filter-nach-kunde-und-status';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-auftrag-liste',
  imports: [CommonModule, FormsModule],
  templateUrl: './auftrag-liste.component.html',
  styleUrl: './auftrag-liste.component.scss'
})
export class AuftragListeComponent implements OnInit {

  kundenliste: Kunde[] = [];
  auftragliste: Auftrag[] = [];
  gefilterteliste: Auftrag[] = [];

  ausgewaehlterKundenId: string = "";
  ausgewaehlterStatus: string = "";

constructor(private kundenService: KundeService,private auftragService: AuftragService , private router: Router, private loginService: LoginService) {}

ngOnInit(): void {

this.seitenAufruf();

}
 seitenAufruf() {
   this.kundenService.getKunde().subscribe(kunden => {
    this.kundenliste = kunden;
  });

  this.auftragService.getAuftraege().subscribe(auftraege => { 
   this.auftragliste = auftraege;
   this.gefilterteliste = auftraege;

  });
 }

btn_route_auftragformular(){

  this.router.navigate(['/auftragformular']);
}

btn_auftragdetail(auftrag: Auftrag) {
  this.router.navigate(['/auftragdetail', auftrag.auftragsId]);
}

// Filteroptionen für Auftrag

 filter(): void {
    let strategy: AuftragsFilterStrategy | null = null;

    const kundeId = Number(this.ausgewaehlterKundenId);
    const status = this.ausgewaehlterStatus;

    if (this.ausgewaehlterKundenId && this.ausgewaehlterStatus) {
      strategy = new FilterNachKundeUndStatus(kundeId, status);
    } else if (this.ausgewaehlterKundenId) {
      strategy = new FilterNachKunde(kundeId);
    } else if (this.ausgewaehlterStatus) {
      strategy = new FilterNachStatus(status);
    }

    this.gefilterteliste = strategy
      ? strategy.filter(this.auftragliste)
      : [...this.auftragliste];
  }

  get isUser(): boolean {
  return this.loginService.isUser();
}




}
