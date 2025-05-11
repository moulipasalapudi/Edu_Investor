import { Component, OnInit } from '@angular/core';

import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { LoanApplication } from '../../models/loanapplication.model';
import { LoanService } from '../../services/loan.service';

@Component({
  selector: 'app-requestedloan',
  templateUrl: './requestedloan.component.html',
  styleUrls: ['./requestedloan.component.css']
})
export class RequestedloanComponent implements OnInit {

  loanApplications: LoanApplication[] = [];
  filteredLoans: LoanApplication[] | undefined;
  selectedLoan: LoanApplication | null = null;
  filterStatus: string = 'All';
  searchTerm: string = '';
  sanitizedFileUrl: SafeUrl | undefined;

  constructor(private loanService: LoanService, private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.fetchLoanApplications();
  }

  fetchLoanApplications(): void {
    this.loanService.getAllLoanApplications().subscribe((data) => {
      this.loanApplications = data;
      this.filteredLoans = data;
    });
  }

  filterLoans(): void {
    this.filteredLoans = this.loanApplications.filter(loan =>
      (this.filterStatus === 'All' || loan.loanStatus.toString() === this.filterStatus) &&
      (loan.institution.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
       loan.course.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
       loan.tuitionFee.toString().includes(this.searchTerm))
    );
  }

  showDetails(loan: LoanApplication): void {
    console.log(loan.file)
    this.selectedLoan = loan;
    this.sanitizedFileUrl = this.sanitizeUrl(this.selectedLoan.file);
    console.log(this.sanitizedFileUrl)
    console.log(this.selectedLoan.file)
    // console.log(this.selectedLoan);
  }

  sanitizeUrl(url: string): SafeUrl {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  closeModal(): void {
    this.selectedLoan = null;
  }

  approveLoan(loan: LoanApplication): void {
    loan.loanStatus = 1;
    this.loanService.updateLoanStatus(loan.loanApplicationId ?? 0, loan).subscribe(() => {
      this.fetchLoanApplications();
    });
  }

  rejectLoan(loan: LoanApplication): void {
    loan.loanStatus = 2;
    this.loanService.updateLoanStatus(loan.loanApplicationId??0, loan).subscribe(() => {
      this.fetchLoanApplications();
    });
  }

}
