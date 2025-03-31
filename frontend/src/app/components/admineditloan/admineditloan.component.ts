// admineditloan.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { LoanService } from 'src/app/services/loan.service';
import { Loan } from 'src/app/models/loan.model';
import { LoanFormService } from 'src/app/helpers/loan-form-helper.service';

@Component({
  selector: 'app-admineditloan',
  templateUrl: './admineditloan.component.html',
  styleUrls: ['./admineditloan.component.css']
})
export class AdminEditLoanComponent implements OnInit {
  loanForm: FormGroup;
  loanId: number;
  isUpdated = false;

  constructor(
    private fb: FormBuilder,
    private loanFormService: LoanFormService,
    private loanService: LoanService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.loanForm = this.fb.group({
      loanType: ['', Validators.required],
      description: ['', Validators.required],
      interestRate: [0, Validators.required],
      maximumAmount: [0, Validators.required],
      repaymentTenure: [0, Validators.required],
      eligibility: ['', Validators.required],
      documentsRequired: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loanId = +this.route.snapshot.paramMap.get('loanId');
    console.log(this.loanId);

    if (!isNaN(this.loanId)) {
      this.loanService.getLoanById(this.loanId).subscribe(
        (loan) => this.loanForm.patchValue(loan),
        (error) => console.log('Failed to load loan details', error)
      );
    } else {
      console.log('Invalid loan ID');
    }
  }

  saveOrUpdateLoan(): void {
    this.loanFormService.saveOrUpdateLoan(this.loanForm, this.loanId, "Loan Updated Successfully");
    this.isUpdated = true;
  }

  goBack(): void {
    this.router.navigate(['admin/view/Loans']);
  }
}
