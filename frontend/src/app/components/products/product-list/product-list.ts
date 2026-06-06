import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { ProductService } from '../../../services/product';
import { ProductFiltersComponent } from '../product-filters/product-filters';
import { CartService } from '../../../services/cart';
import { Product } from '../../../models/product.model';

@Component({
  selector: 'app-product-list',
  imports: [RouterLink, NgFor, NgIf, ProductFiltersComponent],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css'
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  allProducts: Product[] = [];

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
    alert('Producto agregado al carrito');
  }
}
