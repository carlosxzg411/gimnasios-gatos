import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { CartService, CartItem } from '../../../services/cart';
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-cart',
  imports: [NgFor, NgIf, RouterLink],
  templateUrl: './cart.html',
  styleUrl: './cart.css'
})
export class CartComponent implements OnInit {
  items: CartItem[] = [];

  constructor(public cart: CartService, private auth: AuthService, private router: Router) {}

  ngOnInit() { this.cart.getCart().subscribe(i => this.items = i); }

  remove(id: number) { this.cart.removeItem(id); }

  update(id: number, cant: number) { this.cart.updateCantidad(id, cant); }

  checkout() {
    if (!this.auth.isLoggedIn()) { this.router.navigate(['/login']); return; }
    this.router.navigate(['/checkout']);
  }
}
