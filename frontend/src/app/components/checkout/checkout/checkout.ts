import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { OrderService } from '../../../services/order';
import { CartService } from '../../../services/cart';

@Component({
  selector: 'app-checkout',
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './checkout.html',
  styleUrl: './checkout.css'
})
export class CheckoutComponent {
  direccionEnvio = '';
  metodoPago = 'stripe';
  error = '';

  constructor(private orderService: OrderService, public cart: CartService, private router: Router) {}

  get items() { return this.cart.getItems(); }

  submit() {
    if (this.items.length === 0) { this.error = 'El carrito está vacío'; return; }
    const request = {
      direccionEnvio: this.direccionEnvio,
      metodoPago: this.metodoPago,
      items: this.items.map(i => ({
        productId: i.product.id,
        cantidad: i.cantidad,
        personalizacionTexto: i.personalizacionTexto
      }))
    };
    this.orderService.create(request).subscribe({
      next: (order) => {
        this.cart.clear();
        alert('Pedido #' + order.id + ' creado con éxito. Recibirás la factura por email.');
        this.router.navigate(['/productos']);
      },
      error: err => this.error = err.error?.message || 'Error al crear el pedido'
    });
  }
}
