import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-filters',
  imports: [FormsModule],
  templateUrl: './product-filters.html',
  styleUrl: './product-filters.css'
})
export class ProductFiltersComponent {
  tamano = '';
  color = '';
  material = '';
  precioMin?: number;
  precioMax?: number;

  @Output() filterChange = new EventEmitter<any>();

  apply() {
    this.filterChange.emit({
      tamano: this.tamano || undefined,
      color: this.color || undefined,
      material: this.material || undefined,
      precioMin: this.precioMin || undefined,
      precioMax: this.precioMax || undefined
    });
  }

  clear() {
    this.tamano = ''; this.color = ''; this.material = ''; this.precioMin = undefined; this.precioMax = undefined;
    this.apply();
  }
}
