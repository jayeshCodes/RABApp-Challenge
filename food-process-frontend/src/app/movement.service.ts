import { Component, Injectable } from '@angular/core';
import { Movement } from './movement';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MovementService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getMovements(): Observable<Movement[]> {
    return this.http.get<Movement[]>(`${this.apiServerUrl}/movements/all`, {
      headers:this.createAuthorizationHeader()
    });
  }

  public addMovement(movement: Movement): Observable<Movement> {
    const auth_token = this.getToken();
    const headers = new HttpHeaders({
      'Content-Type' : 'application/json',
      'Authorization' : `Bearer ${auth_token}`
    })
    return this.http.post<Movement>(
      `${this.apiServerUrl}/movements/add`,
      movement, {
        headers: headers,
      }
    );
  }

  public updateMovement(id: number): Observable<Movement> {
    return this.http.put<Movement>(
      `${this.apiServerUrl}/movements/update/${id}`,
      id, {
        headers:this.createAuthorizationHeader()
      }
    );
  }

  public deleteMovement(id: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiServerUrl}/movements/delete/${id}`, {
        headers:this.createAuthorizationHeader()
      }
    );
  }

  private getToken():string{
    const jwtToken = localStorage.getItem('jwt');
    if(jwtToken){
      return jwtToken;
    }
    return '';
  }

  private createAuthorizationHeader() : HttpHeaders{
    const jwtToken = localStorage.getItem('jwt');
    if(jwtToken){
      return new HttpHeaders().set(
        "Authorization","Bearer "+jwtToken
      )
    }else {
      alert("Not Authorized!");
    }
    return new HttpHeaders();
  }
}
