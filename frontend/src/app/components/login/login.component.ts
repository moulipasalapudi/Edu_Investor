import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Login } from 'src/app/models/login.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loginMessage: string = "";

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void { }

  onLogin() {
    if (this.loginForm.invalid) {
      console.log("Form is not valid");
      return;
    }

    const loginData: Login = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password
    };

    this.authService.login(loginData).subscribe(
      (response) => {
        this.loginMessage = "Login Successful";
        console.log(this.loginMessage);
        this.router.navigate(['/']);
      },
      (error) => {
        this.loginMessage = "Login Failed";
        console.log(this.loginMessage);
      }
    );
  }
}
