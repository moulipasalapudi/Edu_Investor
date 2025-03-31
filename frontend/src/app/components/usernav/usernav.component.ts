import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthHelperService } from 'src/app/helpers/auth-helper.service';
 
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
