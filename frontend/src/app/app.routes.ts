import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login';
import { RegisterComponent } from './components/auth/register/register';
import { ProductListComponent } from './components/products/product-list/product-list';
import { ProductDetailComponent } from './components/products/product-detail/product-detail';
import { CartComponent } from './components/cart/cart/cart';
import { CheckoutComponent } from './components/checkout/checkout/checkout';
import { CustomRequestComponent } from './components/customization/custom-request/custom-request';
import { DashboardComponent } from './components/admin/dashboard/dashboard';
import { ProductManagerComponent } from './components/admin/product-manager/product-manager';
import { OrderManagerComponent } from './components/admin/order-manager/order-manager';
import { CustomRequestsManagerComponent } from './components/admin/custom-requests-manager/custom-requests-manager';

export const routes: Routes = [
  { path: '', redirectTo: '/productos', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'productos', component: ProductListComponent },
  { path: 'productos/:id', component: ProductDetailComponent },
  { path: 'cart', component: CartComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'custom-request', component: CustomRequestComponent },
  { path: 'admin', component: DashboardComponent },
  { path: 'admin/products', component: ProductManagerComponent },
  { path: 'admin/orders', component: OrderManagerComponent },
  { path: 'admin/custom-requests', component: CustomRequestsManagerComponent },
  { path: '**', redirectTo: '/productos' }
];
