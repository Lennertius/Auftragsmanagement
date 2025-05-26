import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { NavigateToArtikelCommand } from '../../../commands/navigate-to-artikel';
import { NavigateToAuftragCommand } from '../../../commands/navigate-to-auftrag';
import { NavigateToHomeCommand } from '../../../commands/navigate-to-home';
import { NavigateToKundeCommand } from '../../../commands/navigate-to-kunde';
import { ThemeService } from '../../../services/theme.service'; 
import { LoginService } from '../../../services/login.service';



@Component({
  selector: 'app-navbar',
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

constructor(private router:Router, private themeService: ThemeService, public loginService: LoginService){}

btn_Home_Route():void {
const command = new NavigateToHomeCommand(this.router);
command.execute();
}

btn_Kunde_Route():void {
  const command = new NavigateToKundeCommand(this.router);
  command.execute();
}

btn_Auftrag_Route():void {
  const command = new NavigateToAuftragCommand(this.router);
  command.execute();
}

btn_Artikel_Route():void {
  const command = new NavigateToArtikelCommand(this.router);
  command.execute();
}


logout(): void {
this.loginService.logout();
this.router.navigate(['/loginpage'])
}

toggleTheme(): void {
  this.themeService.toggleTheme();
}

isUser(): boolean {
  return this.loginService.isUser();
}




}