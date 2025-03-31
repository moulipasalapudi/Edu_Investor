import { LoanApplication } from './loanapplication.model';

describe('LoanApplication Model', () => {

  fit('Frontend_LoanApplication_model_should_create_an_instance', () => {
    // Create a sample LoanApplication object
    const loanApplication: LoanApplication = {
    
      submissionDate: '2024-07-15',
      institution: 'XYZ University',
      course: 'Computer Science',
      tuitionFee: 30000,
      loanStatus: 1,
      address: '123 Main St, Anytown, USA',
      file: 'document.pdf'
    };

    expect(loanApplication).toBeTruthy();
    expect(loanApplication.institution).toBe('XYZ University');
   
  });

});
