import { Component, OnInit } from '@angular/core';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminService } from '../../../services/admin';
import { ProductService } from '../../../services/product';
import { Product } from '../../../models/product.model';

@Component({
  selector: 'app-product-manager',
  imports: [NgFor, NgIf, FormsModule],
  templateUrl: './product-manager.html',
  styleUrl: './product-manager.css'
})
export class ProductManagerComponent implements OnInit {
  products: Product[] = [];
  editing: boolean = false;
  form: Partial<Product> = {};
  editId?: number;

  constructor(private admin: AdminService, private productService: ProductService) {}

  ngOnInit() { this.load(); }

  load() { this.productService.getAll().subscribe(d => this.products = d); }

  save() {
    const obs = this.editing && this.editId
      ? this.admin.updateProduct(this.editId, this.form as Product)
      : this.admin.createProduct(this.form as Product);
    obs.subscribe({next: () => { this.load(); this.cancel(); }});
  }

  edit(p: Product) {
    this.editing = true;
    this.editId = p.id;
    this.form = {...p};
  }

  cancel() { this.editing = false; this.form = {}; this.editId = undefined; }

  delete(id: number) {
    if (confirm('¿Eliminar producto?')) this.admin.deleteProduct(id).subscribe(() => this.load());
  }

  newProduct() {
    this.editing = true;
    this.form = {nombre: '', descripcion: '', precio: 0, tamano: '', color: '', material: '', stock: 0, imagenUrl: '', activo: true};
  }
}
