import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms'; // Import Validators
import { JwtService } from '../service/jwt.service';
import { Router } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(private service: JwtService, private fb: FormBuilder, private router:Router) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group(
      {
        name: ['', [Validators.required]],
        email: ['', [Validators.required, Validators.email]], // Add email validation
        password: ['', [Validators.required]],
        confirmPassword: ['', [Validators.required]],
      },
      { validator: this.passwordMatchValidator }
    ); // Add password matching validator
  }

  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      formGroup.get('confirmPassword')?.setErrors({ passwordMismatch: true });
    } else {
      formGroup.get('confirmPassword')?.setErrors(null);
    }
  }

  onSubmit(): void {
    if (this.registerForm.invalid) {
      alert('Please fill in all fields correctly.');
      return;
    }

    this.service.signup(this.registerForm.value).subscribe(
      (response) => {
        this.router.navigate(['login']);
      },
      (error : HttpErrorResponse) => {
        if(error.status===201){
          alert("Signed up successfully! Please login.")
          this.router.navigate(['login']);
        }else {
          alert("failed to create user: server error");
        }
      }
    );
  }
}
