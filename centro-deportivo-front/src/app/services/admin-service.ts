import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Admin } from '../models/Admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private readonly URL = `${environment.apiUrl}`;

  constructor(private http: HttpClient) { }

  getAdmin() {
    return this.http.get<Admin>(`${this.URL}/admin/users/admin_carlos`);
  }
}
