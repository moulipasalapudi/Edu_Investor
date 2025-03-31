import { Component, OnInit } from '@angular/core';
import { AuthService } from './services/auth.service';
import { AuthHelperService } from './helpers/auth-helper.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isLoggedIn = false;
  userRole: string | null = null;
  isAdmin=false;
  isUser=false;

  constructor(public authService: AuthHelperService) {}
  ngOnInit(): void {
  }

//   ngOnInit(): void {
//     this.isLoggedIn = this.authService.isAuthenticated();
//     if (this.isLoggedIn) {
//       this.userRole = this.authService.getUserRole();}
//     this.isAdmin=this.authService.isAdmin();
//     this.isUser=this.authService.isUser();


// }

}