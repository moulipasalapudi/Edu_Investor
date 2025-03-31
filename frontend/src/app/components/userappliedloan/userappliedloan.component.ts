import { Component, OnInit } from '@angular/core';
import { LoanApplication } from 'src/app/models/loanapplication.model';
import { LoanService } from 'src/app/services/loan.service';
import { Loan } from 'src/app/models/loan.model';

@Component({
  selector: 'app-userappliedloan',
  templateUrl: './userappliedloan.component.html',
  styleUrls: ['./userappliedloan.component.css']
})
export class UserappliedloanComponent implements OnInit {
  appliedLoans: LoanApplication[] = [];
  loans: Loan[] = [];
  searchText: string = '';
  showDeletePopup: boolean = false;
  loanToDelete: number | null = null;

  constructor(private loanService: LoanService) {}

  ngOnInit(): void {
    const userId = +sessionStorage.getItem('userId'); 
    this.fetchAppliedLoans(userId);
    this.fetchLoans();
  }

  fetchAppliedLoans(userId: number): void {
    this.loanService.getAppliedLoans(userId).subscribe(
      (data) => {
        this.appliedLoans = data;
        console.log('Applied Loans:', this.appliedLoans);
      },
      (error) => {
        console.error('Error fetching applied loans:', error);
      }
    );
  }

  fetchLoans(): void {
    this.loanService.getAllLoans().subscribe(
      (data) => {
        this.loans = data;
        console.log(this.loans);
      },
      (error) => {
        console.error('Error fetching loans:', error);
      }
    );
  }

  filteredLoans(): LoanApplication[] {
    return this.appliedLoans.filter((loan) =>
      loan.submissionDate.toLowerCase().includes(this.searchText.toLowerCase()) ||
      loan.loanStatus.toString().toLowerCase().includes(this.searchText.toLowerCase()));
  }

  getLoanType(loanId: number | undefined): string {
    const loan = this.loans.find((loan) => loan.loanId === loanId);
    return loan ? loan.loanType : 'Unknown';
  }

  confirmDelete(loanId: number): void {
    this.loanToDelete = loanId;
    this.showDeletePopup = true;
  }

  deleteLoan(): void {
    if (this.loanToDelete !== null) {
      this.loanService.deleteLoanApplication(this.loanToDelete).subscribe(
        () => {
          this.appliedLoans = this.appliedLoans.filter((loan) => loan.loanApplicationId !== this.loanToDelete);
          this.showDeletePopup = false;
          this.loanToDelete = null;
        },
        (error) => {
          console.error('Error deleting loan application:', error);
        }
      );
    }
  }

  cancelDelete(): void {
    this.showDeletePopup = false;
    this.loanToDelete = null;
  }
}
