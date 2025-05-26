import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Router, NavigationEnd } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar/navbar.component';
import { filter } from 'rxjs/operators';
import { CommonModule } from '@angular/common';
import { LoginService } from './services/login.service';
@Component({
  selector: 'app-root',
  imports: [NavbarComponent, RouterOutlet, CommonModule],
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

   title = 'Auftragsmanagement';
  showNavbar = true

    constructor(
    public loginService: LoginService,
    public router: Router
  ) {}

  get username(): string | null {
    return this.loginService.getUsername();
  }
}

