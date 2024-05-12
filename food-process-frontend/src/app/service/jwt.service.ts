import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private http:HttpClient) { }

    signup(signupRequest:any):Observable<any>{
      return this.http.post(environment.baseUrl+'signup', signupRequest)
    }

    login(loginRequest: any):Observable<any>{
      return this.http.post(environment.baseUrl+'login', loginRequest)
    }
}
