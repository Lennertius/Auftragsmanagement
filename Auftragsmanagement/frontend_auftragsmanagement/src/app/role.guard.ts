import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';
import { LoginService } from './services/login.service';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles: string[] = route.data['roles'];
    const currentRole = this.loginService.getRole();

    if (currentRole && expectedRoles.includes(currentRole)) {
      return true;
    }

  
    this.router.navigate(['/landingpage']);
    return false;
  }
}
