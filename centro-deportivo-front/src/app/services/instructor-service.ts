import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import SportActivitySummary from '../models/SportActivitySummary'; 
import { environment } from '../../environments/environment';
import Instructor from '../models/Instructor'; 
import { PageableResponse } from '../models/Pageable';
import { Member } from '../models/Member';


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
  
  getAllMembers(page: number, size: number): Observable<PageableResponse<Member>> {
    let params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString());
    
    return this.http.get<PageableResponse<Member>>(`${this.URL}/members`,{params});
  }
}