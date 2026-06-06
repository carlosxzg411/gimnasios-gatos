import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink, NgIf],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class RegisterComponent {
  nombre = '';
  email = '';
  password = '';
  direccion = '';
  telefono = '';
  error = '';

  constructor(private auth: AuthService, private router: Router) {}

  onSubmit() {
    this.auth.register({nombre: this.nombre, email: this.email, password: this.password, direccion: this.direccion, telefono: this.telefono})
      .subscribe({
        next: () => this.router.navigate(['/productos']),
        error: err => this.error = err.error?.message || 'Error al registrarse'
      });
  }
}
