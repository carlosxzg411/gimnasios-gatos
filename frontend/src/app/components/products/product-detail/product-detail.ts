import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { NgIf } from '@angular/common';
import { ProductService } from '../../../services/product';
import { CartService } from '../../../services/cart';
import { Product } from '../../../models/product.model';

@Component({
  selector: 'app-product-detail',
  imports: [NgIf, RouterLink],
  templateUrl: './product-detail.html',
  styleUrl: './product-detail.css'
})
export class ProductDetailComponent implements OnInit {
  product?: Product;
  loading = true;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cart: CartService
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id) { this.error = 'ID de producto inválido'; this.loading = false; return; }
    this.productService.getById(id).subscribe({
      next: data => { this.product = data; this.loading = false; },
      error: err => { this.error = err.error?.message || 'Error al cargar el producto'; this.loading = false; }
    });
  }

  addToCart() {
    if (this.product) {
      this.cart.addItem(this.product);
      alert('Producto agregado al carrito');
    }
  }
}
