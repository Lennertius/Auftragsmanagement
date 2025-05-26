import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KundeService } from '../../services/kunde.service';
import { ActivatedRoute } from '@angular/router';
import { Kunde } from '../../interfaces/interfaces.model';
import { Router } from '@angular/router';
import { NavigateToKundeCommand } from '../../commands/navigate-to-kunde';
import { LoginService } from '../../services/login.service';


@Component({
  selector: 'app-kunde',
  imports: [CommonModule],
  templateUrl: './kunde.component.html',
  styleUrl: './kunde.component.scss'
})
export class KundeComponent implements OnInit {

kunde?: Kunde;

constructor(private kundeService: KundeService, private route: ActivatedRoute, private  router:Router, private loginService: LoginService) {}

ngOnInit(): void {
const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.kundeService.getKundeById(id).subscribe(data => {
        this.kunde = data;
      });          
    }
  }

  deleteKunde() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id) {
      this.kundeService.deleteKundeById(id).subscribe(data => {
         const command = new NavigateToKundeCommand(this.router);
          command.execute();
      })
    }
  }

  back() {
    const command = new NavigateToKundeCommand(this.router);
    command.execute();
  }

  get isEditor(): boolean {
  return this.loginService.isEditor();
}

  get isAdmin(): boolean {
  return this.loginService.isAdmin();
}

}