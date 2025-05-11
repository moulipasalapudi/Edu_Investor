import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Loan } from '../../models/loan.model';
import { LoanService } from '../../services/loan.service';
import { LoanApplication } from '../../models/loanapplication.model';


@Component({
  selector: 'app-userviewloan',
  templateUrl: './userviewloan.component.html',
  styleUrls: ['./userviewloan.component.css']
})
export class UserviewloanComponent implements OnInit {
 
  loan: Loan[] = [];
  appliedLoans: any[] = [];
  userId: number | null = null;
  loading: boolean = true;

  constructor(
    private loanService: LoanService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const userIdString = sessionStorage.getItem('userId');
    this.userId = userIdString ? +userIdString : null;
    if (this.userId) {
      this.fetchLoans();
      this.fetchAppliedLoans();
    }
  }

  fetchLoans(): void {
    this.loanService.getAllLoans().subscribe(
      (data) => {
        this.loan = data;
        console.log("Loans"+this.loan);
        console.log("Id:"+this.loan[0]);
        console.log("Loans: " + JSON.stringify(this.loan, null, 2));
     
        
        
        this.loading = false;
      },
      (error) => {
        console.error('Error fetching loans:', error);
        this.loading = false;
      }
    );
  }

  fetchAppliedLoans(): void {
    if (this.userId !== null) {
      this.loanService.getAppliedLoans(this.userId).subscribe(
        (data) => {
          this.appliedLoans = data;
          console.log("AppliedLoans"+this.appliedLoans);
          console.log("Loans: " + JSON.stringify(this.appliedLoans, null, 2));
          
        },
        (error) => {
          console.error('Error fetching applied loans:', error);
        }
      );
    }
  }

  isApplied(loanId: number): boolean {
    
    return this.appliedLoans.some((l) => l.loan.loanId=== loanId);
  }

applyLoan(loanId: number): void {
  console.log(loanId);
  this.markAsApplied(loanId);
  this.router.navigate(['/user/loanform', loanId]);
}

  markAsApplied(loanId: number): void {
    console.log("UserId"+this.userId);
    console.log("LoanId"+loanId);
    
    if (this.userId !== null) {
      this.appliedLoans.push({ loanId, userId: this.userId } as LoanApplication);
    }
  }
}
