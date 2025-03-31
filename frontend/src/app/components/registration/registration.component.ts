import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
 
 
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  successMessage:string='';
  errorMessage:string='';
  constructor(private authService: AuthService,private router:Router) { }
 
  ngOnInit(): void {
   
  }
 
 
  register(form:NgForm){
    console.log(form.value)
    if(form.valid){
      this.authService.register(form.value).subscribe({
        next: (registeredUser) => {
          this.successMessage = 'Registration Successful!';
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        },
        error: (err) => {
          this.errorMessage = 'User already exists';
        }
      });
    }
  }
}