import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import SportActivity from '../models/SportActivity'; 
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class InstructorService {

  private readonly URL = `${environment.apiUrl}/activities`;  
  constructor(private http: HttpClient) { }
   
  getActivitiesByInstructor(instructorId: number): Observable<SportActivity[]> {
    
    return this.http.get<SportActivity[]>(`${this.URL}/instructors/${instructorId}/activities`);
  }
}