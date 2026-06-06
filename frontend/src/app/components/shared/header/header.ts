import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth';
import { CartService } from '../../../services/cart';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-header',
  imports: [RouterLink, NgIf],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class HeaderComponent {
  constructor(public auth: AuthService, public cart: CartService, private router: Router) {}

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
