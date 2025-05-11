import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Loan } from '../../models/loan.model';
import { LoanService } from '../../services/loan.service';

@Component({
  selector: 'app-viewloan',
  templateUrl: './viewloan.component.html',
  styleUrls: ['./viewloan.component.css']
})
export class ViewloanComponent implements OnInit {
  loans: Loan[] = [
    {
      loanId: 1,
      loanType: 'Personal Loan',
      description: 'A personal loan to cover unexpected expenses.',
      interestRate: 5.5,
      maximumAmount: 50000,
      repaymentTenure: 24,
      eligibility: 'Minimum salary of $25,000',
      documentsRequired: 'ID proof, Address proof, Salary slip'
    }
  ];
  searchTerm: string = '';
  errorMessage: string = '';

  constructor(private loanService: LoanService, private router: Router) { }

  ngOnInit(): void {
    this.loadLoans();
  }

  loadLoans(): void {
    this.loanService.getAllLoans().subscribe(
      (data: Loan[]) => (this.loans = data),
      (error) => (this.errorMessage = 'Error loading loans')
    );
  }

  get filteredLoans(): Loan[] {
    return this.loans.filter(loan =>
      loan.loanType.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      loan.description.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  confirmDeleteLoan(loanId: number): void {
    if (confirm('Are you sure you want to delete this loan?')) {
      this.deleteLoan(loanId);
    }
  }

  deleteLoan(loanId: number): void {
    this.loanService.deleteLoan(loanId).subscribe(
      () => {
        this.loans = this.loans.filter(loan => loan.loanId !== loanId);
        alert('Loan deleted successfully');
      },
      (error) => {
        this.errorMessage = 'Error deleting loan';
        console.error('Error deleting loan', error);
      }
    );
  }

  editLoan(loan: Loan): void {
    if (loan.loanId) {
      this.router.navigate(['/admin/editLoan', loan.loanId]);
    } else {
      this.errorMessage = 'Invalid Loan Id';
      console.log('Invalid Loan Id', loan);
    }
  }
}
