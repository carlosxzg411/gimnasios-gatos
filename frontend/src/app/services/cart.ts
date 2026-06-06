import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Product} from '../models/product.model';

export interface CartItem {
  product: Product;
  cantidad: number;
  personalizacionTexto?: string;
}

@Injectable({providedIn: 'root'})
export class CartService {
  private items: CartItem[] = [];
  private cartSubject = new BehaviorSubject<CartItem[]>([]);

  getCart(): Observable<CartItem[]> {
    return this.cartSubject.asObservable();
  }

  getItems(): CartItem[] {
    return this.items;
  }

  addItem(product: Product, cantidad: number = 1, personalizacion?: string) {
    const existing = this.items.find(i => i.product.id === product.id);
    if (existing) {
      existing.cantidad += cantidad;
    } else {
      this.items.push({product, cantidad, personalizacionTexto: personalizacion});
    }
    this.cartSubject.next([...this.items]);
  }

  removeItem(productId: number) {
    this.items = this.items.filter(i => i.product.id !== productId);
    this.cartSubject.next([...this.items]);
  }

  updateCantidad(productId: number, cantidad: number) {
    const item = this.items.find(i => i.product.id === productId);
    if (item) {
      item.cantidad = cantidad;
      if (item.cantidad <= 0) this.removeItem(productId);
      else this.cartSubject.next([...this.items]);
    }
  }

  getTotal(): number {
    return this.items.reduce((sum, i) => sum + i.product.precio * i.cantidad, 0);
  }

  getCount(): number {
    return this.items.reduce((sum, i) => sum + i.cantidad, 0);
  }

  clear() {
    this.items = [];
    this.cartSubject.next([]);
  }
}
