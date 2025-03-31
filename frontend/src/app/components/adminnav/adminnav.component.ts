import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthHelperService } from 'src/app/helpers/auth-helper.service';

@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {

  showLogoutConfirmation = false;
  username: string = '';

  constructor(private router: Router, private authHelper: AuthHelperService) { }

  ngOnInit(): void {
    this.getUsernameFromToken();
  }

  getUsernameFromToken(): void {
    const token = sessionStorage.getItem('jwtToken');
    if (token) {
      const decodedToken = this.authHelper.decodeToken(token);
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
