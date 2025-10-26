import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import SportActivitySummary from '../models/SportActivitySummary'; 
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class InstructorService {

  private readonly URL = `${environment.apiUrl}/activities`;  
  constructor(private http: HttpClient) { }
   
  getActivitiesByInstructor(): Observable<SportActivitySummary[]> {  
    return this.http.get<SportActivitySummary[]>(`${this.URL}/instructors/my-activities`);
  }
}