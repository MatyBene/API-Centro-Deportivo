import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  imports: [ReactiveFormsModule],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css'
})
export class LoginPage {
  loginForm: FormGroup;
  isLoading = false;
  errorMessage: string = '';
  

  constructor(
    private fb: FormBuilder, 
    private authService: AuthService,
    private router: Router
  ){
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  onSubmit(){
    if(this.loginForm.valid && !this.isLoading){
      this.isLoading = true;
      const {username, password} = this.loginForm.value;

      this.authService.login(username, password).subscribe({
        next: (data) => {
          this.isLoading = false;
          alert('El usuario ingreso correctamente');
          console.log(this.authService.getUserRole());
          this.router.navigate(['/members/profile']);
          console.log('Login exitoso', data);
        },
        error: (error) => {
          this.isLoading = false;
          this.errorMessage = error.error.message;
          console.log('Error: ', error.error.message);
        }
      })
    }
  }
}
