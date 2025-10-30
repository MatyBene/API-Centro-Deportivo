import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Admin } from '../models/Admin';
import { Member } from '../models/Member';
import Instructor from '../models/Instructor';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private readonly URL = `${environment.apiUrl}/admin`;

  constructor(private http: HttpClient) { }

  getAdmin() {
    return this.http.get<Admin>(`${this.URL}/profile`);
  }

  registerMember(member: Member) {
    return this.http.post(`${this.URL}/create-member`, member, {responseType: 'text'});
  }

  registerInstructor(instructor: Instructor) {
    return this.http.post(`${this.URL}/create-instructor`, instructor, {responseType: 'text'});
  }
}
