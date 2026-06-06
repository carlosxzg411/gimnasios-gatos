import { Component, OnInit } from '@angular/core';
import { NgFor, DatePipe, SlicePipe } from '@angular/common';
import { AdminService } from '../../../services/admin';
import { CustomRequest } from '../../../models/custom-request.model';

@Component({
  selector: 'app-custom-requests-manager',
  imports: [NgFor, DatePipe, SlicePipe],
  templateUrl: './custom-requests-manager.html',
  styleUrl: './custom-requests-manager.css'
})
export class CustomRequestsManagerComponent implements OnInit {
  requests: CustomRequest[] = [];

  constructor(private admin: AdminService) {}

  ngOnInit() { this.admin.getCustomRequests().subscribe(d => this.requests = d); }

  updateStatus(id: number, status: string) {
    this.admin.updateCustomStatus(id, status).subscribe(() =>
      this.admin.getCustomRequests().subscribe(d => this.requests = d));
  }
}
