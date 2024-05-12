import { Component, OnInit } from '@angular/core';
import { JwtService } from '../service/jwt.service';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';
import { response } from 'express';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  loggedIn = false;

  constructor(
    private service: JwtService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required],
    });
  }

  public navigateToSignup(): void {
    this.router.navigate(['signup']);
  }

  public onSubmit(): void {
    const loginRequest = {
      email: this.loginForm.get('email')?.value,
      password: this.loginForm.get('password')?.value,
    };


    this.service.login(loginRequest).subscribe(
      (response) => {
        if(response.jwt!=null){
          console.log("token");
          const jwtToken = response.jwt;
          console.log(jwtToken);
          localStorage.setItem('jwt',jwtToken);
          this.loggedIn = true;
          this.router.navigate(['farm-map'])
        }
      },
      (error) => {
        console.log("No token")
        console.error(error);
      }
    );
  }
}
