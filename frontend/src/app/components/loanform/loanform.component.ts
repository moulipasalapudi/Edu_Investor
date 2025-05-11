import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { LoanService } from '../../services/loan.service';

// import * as mammoth from 'mammoth';
 
@Component({
  selector: 'app-loanform',
  templateUrl: './loanform.component.html',
  styleUrls: ['./loanform.component.css']
})
export class LoanformComponent implements OnInit {
 
  loanForm: FormGroup;
  fileError: boolean = false;
  showSuccessPopup: boolean = false;
  file: { sizeExceeded: boolean } = { sizeExceeded: false };
  loanId: string | null = null;
  userId: string | null = null;
  img: string | undefined;
 
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private loanService: LoanService
  ) {
    this.loanForm = this.fb.group({
      institution: ['', Validators.required],
      course: ['', Validators.required],
      tuitionFee: ['', Validators.required],
      address: ['', Validators.required],
      file: ['', Validators.required]
    });
  }
 
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.loanId = params.get('id');
    });
 
    this.userId = sessionStorage.getItem('userId');
  }
 
 
 
  // onFileChange(event: Event, fileType: string): void {
  //   const input = event.target as HTMLInputElement;
  //   if (input.files && input.files.length > 0) {
  //     const file = input.files[0];
  //     const reader = new FileReader();
 
  //     reader.onload = async () => {
  //       if (fileType === 'file') {
  //         const arrayBuffer = reader.result as ArrayBuffer;
  //         try {
  //           const result = await mammoth.extractRawText({ arrayBuffer });
  //           this.loanForm.patchValue({
  //             file: result.value
  //           });
  //         } catch (error) {
  //           console.error('Error parsing Word document:', error);
  //         }
  //       }
  //     };
 
  //     reader.readAsArrayBuffer(file);
  //   }
  // }
  onFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();
      // reader.readAsDataURL(file);
 
      reader.onload = () => {
          console.log("file Added");
          console.log(reader.result)
          this.img=reader.result as string;
          console.log(this.img)
          this.loanForm.patchValue({
            file: this.img
          });
      };
 
      reader.readAsDataURL(file); // Use readAsDataURL for image files
    }
  }
 
  onSubmit(event: Event) {
    event.preventDefault(); // Prevent default form submission
    if (this.loanForm.valid) {
      const formData = { ...this.loanForm.value, loanId: this.loanId, userId: this.userId };
      this.loanService.addLoanApplication(formData).subscribe(
        () => {
          this.router.navigate(['user/view/Loans'])
          this.showSuccessPopup = true;
        },
        (error) => {
          // Handle error if needed
        }
      );
    } else {
      this.fileError = !this.loanForm.value.file;
    }
  }
 
  closePopup() {
    this.showSuccessPopup = false;
    this.router.navigate(['/user/view/Loans']);
  }
 
  goBack() {
    this.router.navigate(['/user/view/Loans']);
  }
}
 