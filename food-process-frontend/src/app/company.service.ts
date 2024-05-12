import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from './company';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>(`${this.apiServerUrl}/companies/all`, {
      headers: this.createAuthorizationHeader()
    });
  }

  private createAuthorizationHeader(): HttpHeaders {
    const jwtToken = localStorage.getItem('jwt');
    if (jwtToken) {
      return new HttpHeaders().set('Authorization', 'Bearer ' + jwtToken);
    } else {
      alert('Not Authorized!');
    }
    return new HttpHeaders();
  }
}
