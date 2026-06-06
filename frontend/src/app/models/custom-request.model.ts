import {User} from './user.model';

export interface CustomRequest {
  id: number;
  usuario: User;
  descripcion: string;
  productoInteres?: string;
  estado: string;
  fecha: string;
}
