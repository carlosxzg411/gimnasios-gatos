import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Order} from '../models/order.model';

@Injectable({providedIn: 'root'})
export class OrderService {
  private apiUrl = 'http://localhost:8080/api/orders';

  constructor(private http: HttpClient) {}

  create(request: {direccionEnvio: string; metodoPago?: string; items: {productId: number; cantidad: number; personalizacionTexto?: string}[]}): Observable<Order> {
    return this.http.post<Order>(this.apiUrl, request);
  }

  getMyOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl);
  }

  getById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.apiUrl}/${id}`);
  }
}
