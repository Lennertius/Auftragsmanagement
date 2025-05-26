import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private users = [
    { username: 'admin', password: 'admin', role: 'admin' },
    { username: 'user', password: 'user', role: 'user' },
    { username: 'editor', password: 'editor', role: 'editor' }
  ];

  private currentRole: 'admin' | 'editor' | 'user' | null = null;
  private currentUsername: string | null = null;

    constructor() {
    const savedRole = localStorage.getItem('role') as 'admin' | 'user' | 'editor' | null;
    const savedUsername = localStorage.getItem('username');

    if (savedRole && savedUsername) {
      this.currentRole = savedRole;
      this.currentUsername = savedUsername;
    }
  }


  login(username: string, password: string): boolean {
    const user = this.users.find(u => u.username === username && u.password === password);
    if (user) {
      this.currentRole = user.role as 'admin' | 'user' | 'editor';
      this.currentUsername = user.username;

      localStorage.setItem('role', this.currentRole);
      localStorage.setItem('username', this.currentUsername);

      return true;
    }
    return false;
  }

 logout(): void {
  this.currentRole = null;
  this.currentUsername = null;

  localStorage.removeItem('role');
  localStorage.removeItem('username');
}

  getRole(): 'admin' | 'editor' | 'user' | null {
    return this.currentRole;
  }

  isAdmin(): boolean {
    return this.currentRole === 'admin';
  }

  isEditor(): boolean {
    return this.currentRole === 'editor';
  }

  isUser(): boolean {
    return this.currentRole === 'user';
  }

  getUsername(): string | null {
    return this.currentUsername;
  }

}

