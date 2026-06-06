import {OrderItem} from './order-item.model';
import {User} from './user.model';

export interface Order {
  id: number;
  usuario: User;
  fecha: string;
  total: number;
  estado: string;
  direccionEnvio: string;
  metodoPago: string;
  items: OrderItem[];
}
