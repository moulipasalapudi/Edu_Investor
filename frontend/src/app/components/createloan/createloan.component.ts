import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoanFormService } from '../../helpers/loan-form-helper.service';

 
@Component({
  selector: 'app-createloan',
  templateUrl: './createloan.component.html',
  styleUrls: ['./createloan.component.css']
})
export class CreateloanComponent implements OnInit {
  loanForm: FormGroup;
  showPopup: boolean = false;
  popupMessage: string = '';
 
  constructor(private fb: FormBuilder, private loanFormService: LoanFormService) {
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
 
  ngOnInit(): void {}
 
  closePopup(): void {
    this.showPopup = false;
  }
 

  saveOrUpdateLoan(): any {
    if (this.loanForm.valid) {
      this.loanFormService.saveOrUpdateLoan(this.loanForm).subscribe({
        next: (response: any) => {
          this.popupMessage = 'Loan saved successfully!';
          this.showPopup = true;
        },
        error: (error: { error: { errorCode: string; }; }) => {
          if (error.error && error.error.errorCode === 'LOAN_ALREADY_EXISTS') {
            this.popupMessage = 'Loan already exists!';
          } else {
            this.popupMessage = 'An unexpected error occurred.';
          }
          this.showPopup = true;
        }
      });
    }
}
}