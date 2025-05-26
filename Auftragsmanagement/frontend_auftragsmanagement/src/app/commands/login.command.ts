import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';

export class LoginCommand {
  constructor(
    private username: string,
    private password: string,
    private loginService: LoginService,
    private router: Router,
    private onFail: () => void
  ) {}   

  
//  Danki Jan für Hilfi
  execute(): void {
    const success = this.loginService.login(this.username, this.password);
    if (success) {
      this.router.navigate(['/landingpage']);
    } else {
      this.onFail();
    }
  }
}
