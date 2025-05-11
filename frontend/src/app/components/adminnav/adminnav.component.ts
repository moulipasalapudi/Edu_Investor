import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';


@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {

  showLogoutConfirmation = false;
  username: string = '';

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.getUsernameFromToken();
  }
   decodeToken(token:string):any{
      return jwtDecode(token);
    }

  getUsernameFromToken(): void {
    const token = sessionStorage.getItem('jwtToken');
    if (token) {
      const decodedToken = this.decodeToken(token);
      this.username=decodedToken?.username;
    }
  }

  logout(): void {
    this.showLogoutConfirmation = true;
  }

  confirmLogout(): void {
    this.showLogoutConfirmation = false;
    console.log('Admin logged out');
    sessionStorage.clear();
    this.router.navigate(['/']);
  }

  cancelLogout(): void {
    this.showLogoutConfirmation = false;
  }
}
