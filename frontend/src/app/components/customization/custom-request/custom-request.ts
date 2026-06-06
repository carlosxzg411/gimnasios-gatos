import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf, DatePipe } from '@angular/common';
import { CustomizationService } from '../../../services/customization';
import { CustomRequest } from '../../../models/custom-request.model';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-custom-request',
  imports: [FormsModule, NgFor, NgIf, DatePipe],
  templateUrl: './custom-request.html',
  styleUrl: './custom-request.css'
})
export class CustomRequestComponent implements OnInit {
  descripcion = '';
  productoInteres = '';
  error = '';
  success = '';
  requests: CustomRequest[] = [];

  constructor(private service: CustomizationService, private auth: AuthService, private router: Router) {}

  ngOnInit() {
    if (this.auth.isLoggedIn()) {
      this.service.getMyRequests().subscribe(data => this.requests = data);
    }
  }

  submit() {
    if (!this.auth.isLoggedIn()) { this.router.navigate(['/login']); return; }
    this.service.create({descripcion: this.descripcion, productoInteres: this.productoInteres || undefined})
      .subscribe({
        next: (r) => {
          this.success = 'Solicitud enviada. Te contactaremos pronto.';
          this.requests.unshift(r);
          this.descripcion = '';
          this.productoInteres = '';
        },
        error: err => this.error = err.error?.message || 'Error al enviar solicitud'
      });
  }
}
