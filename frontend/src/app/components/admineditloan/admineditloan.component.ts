// admineditloan.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoanFormService } from '../../helpers/loan-form-helper.service';
import { LoanService } from '../../services/loan.service';



@Component({
  selector: 'app-admineditloan',
  templateUrl: './admineditloan.component.html',
  styleUrls: ['./admineditloan.component.css']
})
export class AdminEditLoanComponent implements OnInit {
  loanForm: FormGroup;
  loanId: number | undefined;
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
    const loanIdParam = this.route.snapshot.paramMap.get('loanId');
    this.loanId = loanIdParam ? +loanIdParam : undefined;
    console.log(this.loanId);

    if (!isNaN(this.loanId ?? NaN)) {
      this.loanService.getLoanById(this.loanId ?? 0).subscribe(
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
