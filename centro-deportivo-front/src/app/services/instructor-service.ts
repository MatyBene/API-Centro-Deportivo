import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import Instructor from '../models/Instructor'; 
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {
 
  private readonly URL = `${environment.apiUrl}/instructors`;
  
    constructor(private http: HttpClient) { }

    getInstructor(id: number): Observable<Instructor> {
        return this.http.get<Instructor>(`${this.URL}/${id}/details`);
    }
}