import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthHelperService } from '../helpers/auth-helper.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardGuard implements CanActivate {
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    if(!this.authHelper.isAuthenticated()){
      this.router.navigate(['/login']);
      return false;

    }
    const role =   route.data['role'];
    const userRole= sessionStorage.getItem('userRole');
    console.log(role);
    console.log(userRole);
    
    
    if(role && userRole!==role){
      alert("Not authorized");
      this.router.navigate(['/error']);
      return false;
    }
    return true;
  }
  constructor(private authHelper:AuthHelperService,private router:Router){}
 

  
}
