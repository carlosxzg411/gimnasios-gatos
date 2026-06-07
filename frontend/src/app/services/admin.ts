import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Order} from '../models/order.model';
import {Product} from '../models/product.model';
import {CustomRequest} from '../models/custom-request.model';

@Injectable({providedIn: 'root'})
export class AdminService {
  private apiUrl = '/api/admin';

  constructor(private http: HttpClient) {}

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/orders`);
  }

  updateOrderStatus(id: number, status: string): Observable<Order> {
    return this.http.put<Order>(`${this.apiUrl}/orders/${id}/status`, null, {params: {status}});
  }

  getCustomRequests(): Observable<CustomRequest[]> {
    return this.http.get<CustomRequest[]>(`${this.apiUrl}/custom-requests`);
  }

  updateCustomStatus(id: number, status: string): Observable<CustomRequest> {
    return this.http.put<CustomRequest>(`${this.apiUrl}/custom-requests/${id}/status`, null, {params: {status}});
  }

  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.apiUrl}/products`, product);
  }

  updateProduct(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiUrl}/products/${id}`, product);
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/products/${id}`);
  }
}
