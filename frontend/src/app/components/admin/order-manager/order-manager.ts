import { Component, OnInit } from '@angular/core';
import { NgFor, DatePipe } from '@angular/common';
import { AdminService } from '../../../services/admin';
import { Order } from '../../../models/order.model';

@Component({
  selector: 'app-order-manager',
  imports: [NgFor, DatePipe],
  templateUrl: './order-manager.html',
  styleUrl: './order-manager.css'
})
export class OrderManagerComponent implements OnInit {
  orders: Order[] = [];

  constructor(private admin: AdminService) {}

  ngOnInit() { this.load(); }

  load() { this.admin.getOrders().subscribe(d => this.orders = d); }

  updateStatus(id: number, status: string) {
    this.admin.updateOrderStatus(id, status).subscribe(() => this.load());
  }
}
