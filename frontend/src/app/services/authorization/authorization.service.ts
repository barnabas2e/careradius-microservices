import { Observable } from 'rxjs';
import { Transaction } from './../../models/auth/transaction';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const API_URL = 'http://localhost:8181/api/authorization/service/';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationService {

  constructor(private http: HttpClient) { }

  initiate(transaction: Transaction) {
    return this.http.post(API_URL + 'initiate', JSON.stringify(transaction),
     { headers: {'Content-Type': 'application/json; charset-UTF-8'} });
  }

  findAllAuthorizations(): Observable<any> {
    return this.http.get(API_URL + 'all', { headers: {'Content-Type': 'application/json; charset-UTF-8'} });
  }

  findMemberAuthorization(authInternalId: number): Observable<any> {
    return this.http.get(API_URL + 'auth/' + authInternalId,
     { headers: {'Content-Type': 'application/json; charset-UTF-8'} });
  }

  findTransactionOfMember(memberInternalId: number): Observable<any> {
    return this.http.get(API_URL + 'member/' + memberInternalId,
    { headers: {'Content-Type': 'application/json; charset-UTF-8'} });
  }
  
}
