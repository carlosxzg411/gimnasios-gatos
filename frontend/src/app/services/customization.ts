import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CustomRequest} from '../models/custom-request.model';

@Injectable({providedIn: 'root'})
export class CustomizationService {
  private apiUrl = 'http://localhost:8080/api/custom-requests';

  constructor(private http: HttpClient) {}

  create(request: {descripcion: string; productoInteres?: string}): Observable<CustomRequest> {
    return this.http.post<CustomRequest>(this.apiUrl, request);
  }

  getMyRequests(): Observable<CustomRequest[]> {
    return this.http.get<CustomRequest[]>(this.apiUrl);
  }
}
