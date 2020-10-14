import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const API_URL = 'http://localhost:8181/api/member/service/';

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  constructor(private http: HttpClient) { }

  findMemberbyId(memberId: string): Observable<any> {
    return this.http.get(API_URL + '/retrieve/' + memberId,
    { headers: {'Content-Type': 'application/json; charset-UTF-8'} });
  }
}
