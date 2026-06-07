import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {AuthResponse} from '../models/auth-response.model';

@Injectable({providedIn: 'root'})
export class AuthService {
  private apiUrl = '/api/auth';

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, {email, password})
      .pipe(tap(res => this.setSession(res)));
  }

  register(data: {nombre: string; email: string; password: string; direccion?: string; telefono?: string}): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, data)
      .pipe(tap(res => this.setSession(res)));
  }

  private setSession(res: AuthResponse) {
    localStorage.setItem('token', res.token);
    localStorage.setItem('user', JSON.stringify({email: res.email, nombre: res.nombre, rol: res.rol}));
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getUser(): {email: string; nombre: string; rol: string} | null {
    const u = localStorage.getItem('user');
    return u ? JSON.parse(u) : null;
  }

  isAdmin(): boolean {
    return this.getUser()?.rol === 'ADMIN';
  }
}
