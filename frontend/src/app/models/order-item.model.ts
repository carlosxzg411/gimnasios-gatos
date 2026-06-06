import {Product} from './product.model';

export interface OrderItem {
  id: number;
  product: Product;
  cantidad: number;
  precioUnitario: number;
  personalizacionTexto?: string;
}
