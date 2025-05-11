// loan-form.service.ts
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { LoanService } from '../services/loan.service';
import { Loan } from '../models/loan.model';

@Injectable({
  providedIn: 'root'
})
export class LoanFormService {

  constructor(private loanService: LoanService, private router: Router) { }

  saveOrUpdateLoan(form: FormGroup, loanId?: number, successMessage: string = "Loan Saved Successfully"):any{
    if (form.invalid) {
      console.log("Form is not valid");
      return;
    }

    const loan: Loan = {
      loanId: loanId || undefined,
      loanType: form.value.loanType,
      description: form.value.description,
      interestRate: form.value.interestRate,
      maximumAmount: form.value.maximumAmount,
      repaymentTenure: form.value.repaymentTenure,
      eligibility: form.value.eligibility,
      documentsRequired: form.value.documentsRequired
    };

    this.loanService.saveOrUpdateLoan(loan).subscribe(
      () => {
        alert(successMessage);
        this.router.navigate(['admin/view/Loans']);
      },
      (error) => {
        console.log("Error occurred while saving loan");
      }
    );
  }
}
