import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Loan } from '../models/loan.model';
import { HttpClient } from '@angular/common/http';
import { LoanApplication } from '../models/loanapplication.model';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  public apiUrl: string = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  getAllLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>(`${this.apiUrl}/loan`);
  }

  deleteLoan(loanId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/loan/${loanId}`);
  }

  getLoanById(id: number): Observable<Loan> {
    return this.http.get<Loan>(`${this.apiUrl}/loan/${id}`);
  }

  getAppliedLoans(userId: number): Observable<LoanApplication[]> {
    return this.http.get<LoanApplication[]>(`${this.apiUrl}/user/${userId}`);
  }

  deleteLoanApplication(loanId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/loanapplication/${loanId}`);
  }

  addLoanApplication(data: LoanApplication): Observable<LoanApplication> {
    return this.http.post<LoanApplication>(`${this.apiUrl}/loanapplication`, data);
  }

  getAllLoanApplications(): Observable<LoanApplication[]> {
    return this.http.get<LoanApplication[]>(`${this.apiUrl}/loanapplication`);
  }

  updateLoanStatus(id: number, loanApplication: LoanApplication): Observable<LoanApplication> {
    return this.http.put<LoanApplication>(`${this.apiUrl}/loanapplication/${id}`, loanApplication);
  }

  saveOrUpdateLoan(loan: any): Observable<any> {
    if (loan.loanId) {
      return this.http.put<Loan>(`${this.apiUrl}/loan/${loan.loanId}`, loan);
    } else {
      return this.http.post<Loan>(`${this.apiUrl}/loan`, loan);
    }
  }
  
}
