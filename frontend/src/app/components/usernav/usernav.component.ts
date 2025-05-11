import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoanService } from '../../services/loan.service';
import { AuthHelperService } from '../../helpers/auth-helper.service';
 
@Component({
  selector: 'app-usernav',
  templateUrl: './usernav.component.html',
  styleUrls: ['./usernav.component.css']
})
export class UsernavComponent implements OnInit {
  activeDropdown: string | null = null;
  toggleDropdown(menu: string) {
    this.activeDropdown = this.activeDropdown === menu ? null : menu;
  }
  navigateTo(route: string) {
    this.activeDropdown = null;
    this.router.navigate([route]);
  }
  logout() {
    sessionStorage.clear();
    localStorage.clear();
    this.activeDropdown = null;
    alert("Logged out successfully!");

    this.router.navigate(['/login']);
  }
 
 
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
 
 
 
}
