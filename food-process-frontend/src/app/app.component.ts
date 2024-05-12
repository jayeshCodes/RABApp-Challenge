import { Component, NgModule, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Movement } from './movement';
import { MovementService } from './movement.service';
import { Farm } from './farm';
import { FarmService } from './farm.service';
import { response } from 'express';
import { error } from 'console';
import { HttpErrorResponse } from '@angular/common/http';
import { NgFor } from '@angular/common';
import { CommonModule } from '@angular/common';
import { IgxGridModule } from 'igniteui-angular';
import { ElementRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { environment } from '../environments/environment';
import { Router } from '@angular/router';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, IgxGridModule, LoginComponent, SignupComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit{
  dialog: any;

  constructor(private router:Router){}

  isLogIn : boolean = false;
  ngOnInit(): void {
    this.isLogIn = this.isLoggedIn();
      if(!this.isLogIn){
        this.router.navigate(['login']);
      }
  }

  public navigateToFarm():void{
    if(this.isActive(['farm-data','farm-map'])){
      return;
    }else {
      this.router.navigate(['farm-map']);
    }
  }

  public navigateToMovement():void{
    if(this.isActive(['movement-data','movement-map'])){
      return;
    }else {
      this.router.navigate(['movement-map']);
    }
  }

// Method to logout
public logout(): void {
      localStorage.removeItem('jwt');

      this.router.navigate(['login']);
}
  // Method to check if the provided route is the current active route
  isActive(routes: string[]): boolean {
    return routes.some(route => this.router.isActive(route, true));
  }

  // Method to check if the user is logged in
  public isLoggedIn(): boolean {
    // Check if JWT token is present in local storage
    return !!localStorage.getItem('jwt');
  }
}
