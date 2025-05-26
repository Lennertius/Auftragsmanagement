import { Component, OnInit } from '@angular/core';
import { ArtikelService } from '../../services/artikel.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Artikel } from '../../interfaces/interfaces.model';
import { CommonModule } from '@angular/common';
import { NavigateToArtikelCommand } from '../../commands/navigate-to-artikel';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-artikel',
  imports: [CommonModule],
  templateUrl: './artikel.component.html',
  styleUrls: ['./artikel.component.scss']
})
export class ArtikelComponent implements OnInit {
  artikel?: Artikel;

  constructor(
    private route: ActivatedRoute,
    private artikelService: ArtikelService,
    private router: Router,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.artikelService.getArtikelById(id).subscribe(data => {
        this.artikel = data;
      });
    }
  }

    deleteArtikel() {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      if(id) {
        this.artikelService.deleteArtikelById(id).subscribe(data => {
           const command = new NavigateToArtikelCommand(this.router);
            command.execute();
        })
      }
    }

  back() {
    const command = new NavigateToArtikelCommand(this.router);
    command.execute();
  }

  get isUser(): boolean {
  return this.loginService.isUser();
}

  get isAdmin(): boolean {
  return this.loginService.isAdmin();
}

}
