import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NgFor, NgIf, SlicePipe } from '@angular/common';
import { ProductService } from '../../../services/product';
import { ProductFiltersComponent } from '../product-filters/product-filters';
import { CartService } from '../../../services/cart';
import { Product } from '../../../models/product.model';

@Component({
  selector: 'app-product-list',
  imports: [RouterLink, NgFor, NgIf, SlicePipe, ProductFiltersComponent],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css'
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  allProducts: Product[] = [];

  private colorMap: Record<string, string> = {
    'Beige': '#f5e6cc', 'Gris': '#95a5a6', 'Crema': '#fdf5e6',
    'Natural': '#a0845c', 'Blanco': '#f0f0f0', 'Negro': '#2d3436',
    'Verde': '#2ecc71', 'Azul': '#3498db', 'Rojo': '#e74c3c'
  };

  constructor(private productService: ProductService, private cart: CartService) {}

  ngOnInit() {
    this.productService.getAll().subscribe(data => {
      this.allProducts = data;
      this.products = data;
    });
  }

  onFilter(filters: any) {
    this.productService.filtrar(filters).subscribe(data => this.products = data);
  }

  addToCart(product: Product) {
    this.cart.addItem(product);
    alert('✅ Producto agregado al carrito');
  }

  getColorHex(color: string): string {
    return this.colorMap[color] || '#ccc';
  }
}
