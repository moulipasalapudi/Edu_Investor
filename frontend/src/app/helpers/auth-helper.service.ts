import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthHelperService {
  private currentUserId= new BehaviorSubject<number>(0);
  private currentUserRole= new BehaviorSubject<string>("");
  private currentUserName= new BehaviorSubject<string>("");

  
  
  setRole(role:string|null):void{
    this.currentUserRole.next(role);
  }
  setUserId(id:number):void{
    console.log((this.currentUserId.next(id)));

    // this.currentUserId.subscribe(id => console.log(id));
  }
  setUserName(name:string):void{
    this.currentUserName.next(name);
  }
  getUserId():number{
    // console.log(this.currentUserId.value);
    
    return this.currentUserId.value;
  }
  getUserRole():string{
    return this.currentUserRole.value;
  }
  getUserName():string{
    return this.currentUserName.value;
  }

  clearUserData():void{
    sessionStorage.remove('jwtToken');
    this.currentUserId.next(null);
    this.currentUserRole.next(null);
    this.currentUserName.next(null);

  }
  isAuthenticated():any{
    return !!sessionStorage.getItem('jwtToken');
  }
  isAdmin(): boolean{
    return sessionStorage.getItem('userRole')==='ADMIN'
  }
  isUser():boolean{
    return  sessionStorage.getItem('userRole')==='USER'  
  }



  decodeToken(token:string):any{
    return jwtDecode(token);
  }
  // isLoggedIn(): boolean {  
  //      return !!sessionStorage.getItem('jwtToken'); 
  //  }

  constructor() { }
}
