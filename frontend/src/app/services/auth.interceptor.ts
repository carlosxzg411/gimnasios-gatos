import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);
  const token = localStorage.getItem('token');
  if (token) {
    req = req.clone({ headers: req.headers.set('Authorization', `Bearer ${token}`) });
  }
  return next(req).pipe(
    catchError(err => {
      if (err.status === 401) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        router.navigate(['/login']);
      }
      return throwError(() => err);
    })
  );
};
