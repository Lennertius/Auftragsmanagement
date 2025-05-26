import { Component, OnInit } from '@angular/core';
import { KundeService } from '../../../services/kunde.service';
import { Kunde, Kunde_ohne_ID } from '../../../interfaces/interfaces.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { KundeFactory } from '../../../factories/kunde.factory';
import { LoginService } from '../../../services/login.service';

@Component({
  selector: 'app-kunde-liste',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './kunde-liste.component.html',
  styleUrl: './kunde-liste.component.scss'
})
export class KundeListeComponent implements OnInit {

   kundenliste: Kunde[] = [];

   neuerKunde: Kunde_ohne_ID = {
    vorname:  "",
    nachname: "",
    adresse: "",
    telefonnummer: 0,
    email: "",
    status: ""
   };

  constructor(private kundenService: KundeService, private kundeFactory: KundeFactory, private loginService: LoginService) {}

  ngOnInit(): void {

    this.seitenAufruf();

    this.kundenService.getKundeErstelltListener().subscribe(() => {
    this.seitenAufruf(); 
  });

 }

 seitenAufruf() {
   this.kundenService.getKunde().subscribe(data => {
    this.kundenliste = data;
  });
 }

 kundeErstellen(): void{
 const kunde_factory = this.kundeFactory.create(
  this.neuerKunde.vorname,
  this.neuerKunde.nachname,
  this.neuerKunde.email,
  this.neuerKunde.adresse,
  this.neuerKunde.telefonnummer,
  this.neuerKunde.status
  );

 this.kundenService.createKunde(kunde_factory).subscribe({
  next: (response) => {
    this.resetForm();
    this.kundenService.KundelisteUpdate();
      }
    })
 }

resetForm(){
  this.neuerKunde.vorname = "";
  this.neuerKunde.nachname = "";
  this.neuerKunde.email = "";
  this.neuerKunde.adresse = "";
  this.neuerKunde.telefonnummer = 0;
  this.neuerKunde.status= "";
}

get isEdtior(): boolean {
  return this.loginService.isEditor();
}

}
