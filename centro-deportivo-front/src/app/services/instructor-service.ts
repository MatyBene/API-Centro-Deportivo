import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import SportActivitySummary from '../models/SportActivitySummary'; 
import { environment } from '../../environments/environment';
import Instructor from '../models/Instructor'; 
@Injectable({
  providedIn: 'root'
})
export class InstructorService {

  private readonly URL = `${environment.apiUrl}/instructors`;

  constructor(private http: HttpClient) { }
   
  getActivitiesByInstructor(): Observable<SportActivitySummary[]> {  
    return this.http.get<SportActivitySummary[]>(`${this.URL}/my-activities`);
  }

  getInstructor(id: number): Observable<Instructor> {
    return this.http.get<Instructor>(`${this.URL}/${id}/details`);
  }
  
  
}