import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Member } from '../models/Member';

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  private readonly URL = `${environment.apiUrl}/public/register`;

  constructor(private http: HttpClient){}

  register(member: Member){
    return this.http.post(this.URL, member, {responseType: 'text'});
  }
}
