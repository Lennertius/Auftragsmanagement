import { Component, Input, OnInit } from '@angular/core';
import {
  Artikel_mit_Anzahl,
  Auftrag_mit_Artikel,
  Kunde,
} from '../../../interfaces/interfaces.model';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { AuftragService } from '../../../services/auftrag.service';
import { LoginService } from '../../../services/login.service';
import { NavigateToAuftragCommand } from '../../../commands/navigate-to-auftrag';

@Component({
  selector: 'app-auftrag-detail',
  imports: [CommonModule],
  templateUrl: './auftrag-detail.component.html',
  styleUrl: './auftrag-detail.component.scss',
})
export class AuftragDetailComponent implements OnInit {
  kunden: Kunde[] = [];
  artikel: Artikel_mit_Anzahl[] = [];
  @Input() auftrag?: Auftrag_mit_Artikel;
  private auftragService: AuftragService;

  constructor(
    private route: ActivatedRoute,
    AuftragService: AuftragService,
    public loginService: LoginService,
    private router: Router
  ) {
    this.auftragService = AuftragService;
  }

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.auftragService.getAuftraegeById(id).subscribe((data) => {
        this.auftrag = data;
        if (this.auftrag?.positionen) {
          this.artikel = this.auftrag.positionen.map((position) => ({
            artikel: position.artikel,
            menge: position.menge,
          }));
        } else {
          console.warn('Keine Positionen gefunden im Auftrag:', this.auftrag);
        }
      });
    }
  }

  save() {
    console.log('test');
  }

  delete_auftrag() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.auftragService.deleteAuftrag(id).subscribe({
      next: () => {
        const command = new NavigateToAuftragCommand(this.router);
        command.execute();
      },
      error: (err) => {
        console.error('Fehler beim Löschen des Auftrags:', err);
      },
    });
  }

  get isUser(): boolean {
    return this.loginService.isUser();
  }
}
