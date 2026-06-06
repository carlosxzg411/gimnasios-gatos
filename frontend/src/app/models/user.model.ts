export interface User {
  id: number;
  nombre: string;
  email: string;
  direccion?: string;
  telefono?: string;
  rol: string;
  fechaRegistro: string;
  activo: boolean;
}
