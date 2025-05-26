import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ThemeService {
  private currentTheme: 'light' | 'dark';

  constructor() {
    const savedTheme = localStorage.getItem('theme') as 'light' | 'dark' | null;
    this.currentTheme = savedTheme ?? 'light';
    this.setTheme(this.currentTheme);

  }

  setTheme(theme: 'light' | 'dark') {
    this.currentTheme = theme;
    document.body.classList.remove('light', 'dark');
    document.body.classList.add(theme);
    localStorage.setItem('theme', theme);
  }

  toggleTheme() {
    this.setTheme(this.currentTheme === 'light' ? 'dark' : 'light');
  }

  getCurrentTheme(): 'light' | 'dark' {
    return this.currentTheme;
  }
}
