
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { LoginCommand } from '../../commands/login.command';
import { CommonModule } from '@angular/common';
import { ThemeService } from '../../services/theme.service';



@Component({
  selector: 'app-loginpage',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './loginpage.component.html',
   styleUrl: './loginpage.component.scss'
})


export class LoginpageComponent {
  loginForm: FormGroup;
    loginFailed = false;
  showPassword = false;

  constructor( private fb: FormBuilder,
  private loginService: LoginService,
  private router: Router, private themeService: ThemeService) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

onSubmit(): void {
  const { username, password } = this.loginForm.value;

  const command = new LoginCommand(
    username,
    password,
    this.loginService,
    this.router,
    () => this.loginFailed = true
  );

  command.execute();
}

toggleTheme(): void {
  this.themeService.toggleTheme();
}

}



