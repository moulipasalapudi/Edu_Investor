import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminEditLoanComponent } from './admineditloan.component';

describe('AdmineditloanComponent', () => {
  let component: AdminEditLoanComponent;
  let fixture: ComponentFixture<AdminEditLoanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminEditLoanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminEditLoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
