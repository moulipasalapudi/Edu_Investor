import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../models/user.model';
import { Login } from '../models/login.model';
import { tap } from 'rxjs/operators'; 
import { AuthHelperService } from '../helpers/auth-helper.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private tokenKey = 'authToken';
  private userRoleSubject = new BehaviorSubject<string | null>(null);
  private userIdSubject = new BehaviorSubject<number | null>(null);


  public apiUrl = "http://localhost:8080/api"
  // public apiUrl="https://ide-afacbbedadbcbecadbbfbaebabfcfdfcdfdeacdcff.premiumproject.examly.io/proxy/3001"
  constructor(private http:HttpClient,private authHelper:AuthHelperService) { }

  register(user:User):Observable<any>{
    console.log("AuthService: ", user);
    // return this.http.post<any>(this.apiUrl+'/users', user);
    return this.http.post<any>(this.apiUrl+'/register', user);
  }

  login(login:Login):Observable<any>{
    return this.http.post<any>(this.apiUrl+'/login',login).pipe(tap(response=>{      
      sessionStorage.setItem('jwtToken', response.token);
      const decodedToken: any = this.authHelper.decodeToken(response.token);
      this.authHelper.setRole(decodedToken?.role);
      console.log()
      this.authHelper.setUserName(decodedToken?.username);
      this.authHelper.setUserId(Number(decodedToken?.userId))
      console.log("UserName:",this.authHelper.getUserName());
      console.log(this.authHelper.getUserId());
      
      
      // this.authHelper.setUserId(decodedToken?.id);
      console.log("Role",this.authHelper.getUserRole());
      // console.log(this.authHelper.getUserId());
      console.log("Token",decodedToken);


      sessionStorage.setItem('userId', decodedToken?.userId)
      sessionStorage.setItem('userRole', decodedToken?.role)
      
      
    })
    )

    // return this.http.get<any>(this.apiUrl+'/users?email='+login.email);
    // return this.http.post<any>(this.apiUrl+'/login',login)
  }

 
 
  

}


