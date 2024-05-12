import { Injectable } from '@angular/core';
import { Farm } from './farm';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FarmService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getFarms(): Observable<Farm[]> {
    return this.http.get<Farm[]>(`${this.apiServerUrl}/farms/all`,{
      headers:this.createAuthorizationHeader()
    });
  }
  public addFarm(farm: Farm): Observable<Farm> {
    return this.http.post<Farm>(`${this.apiServerUrl}/farms/add`, farm, {
      headers:this.createAuthorizationHeader()
    });
  }
  public updateFarms(farm: Farm): Observable<Farm> {
    return this.http.put<Farm>(`${this.apiServerUrl}/farms/update`, farm, {
      headers:this.createAuthorizationHeader()
    });
  }
  public deleteFarms(premiseId: String): Observable<void>{
    return this.http.delete<void>(`${this.apiServerUrl}/farms/delete/${premiseId}`,{
      headers:this.createAuthorizationHeader()
    });
  }

  private createAuthorizationHeader() : HttpHeaders{
    const jwtToken = localStorage.getItem('jwt');
    if(jwtToken){
      console.log("token is",jwtToken);
      return new HttpHeaders().set(
        "Authorization","Bearer "+jwtToken
      )
    }else {
      alert("Not Authorized!");
    }
    return new HttpHeaders();
  }
}
