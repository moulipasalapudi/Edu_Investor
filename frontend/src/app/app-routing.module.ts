import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { CreateloanComponent } from './components/createloan/createloan.component';
import { AdminEditLoanComponent } from './components/admineditloan/admineditloan.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { HomeComponent } from './components/home/home.component';
import { ErrorComponent } from './components/error/error.component';
import { UserviewloanComponent } from './components/userviewloan/userviewloan.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { RequestedloanComponent } from './components/requestedloan/requestedloan.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { ViewloanComponent } from './components/viewloan/viewloan.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UserappliedloanComponent } from './components/userappliedloan/userappliedloan.component';
import { AuthguardGuard } from './components/authguard.guard';
import { LoanformComponent } from './components/loanform/loanform.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'error', component: ErrorComponent},
  { path: 'login', component: LoginComponent },
  { path: 'adminnav', component: AdminnavComponent, canActivate: [AuthguardGuard], data: { role: 'ADMIN' } },
  { path: 'usernav', component: UsernavComponent, canActivate: [AuthguardGuard], data: { role: 'USER' }},
  { path: 'register', component: RegistrationComponent},
  { path: 'admin/add/newLoan', component: CreateloanComponent, canActivate: [AuthguardGuard], data: { role: 'ADMIN' }},
  { path: 'admin/view/Loans', component: ViewloanComponent, canActivate: [AuthguardGuard], data: { role: 'ADMIN' }},
  { path: 'admin/view/Feedbacks', component: AdminviewfeedbackComponent, canActivate: [AuthguardGuard], data: { role: 'ADMIN' }},
  { path: 'admin/editLoan/:loanId', component: AdminEditLoanComponent, canActivate: [AuthguardGuard], data: { role: 'ADMIN' }},
  { path: 'user/view/Loans', component: UserviewloanComponent, canActivate: [AuthguardGuard], data: { role: 'USER' }},
  { path: 'user/view/Feedbacks', component: UserviewfeedbackComponent, canActivate: [AuthguardGuard], data: { role: 'USER' }},
  { path: 'admin/requestLoan', component: RequestedloanComponent, canActivate: [AuthguardGuard], data: { role: 'ADMIN' }},
  { path: 'user/loanform/:id', component: LoanformComponent, canActivate: [AuthguardGuard], data: { role: 'USER' }},
  { path: 'user/view/Loans', component: UserviewloanComponent, canActivate: [AuthguardGuard], data: { role: 'USER' }},
  { path: 'user/view/Feedbacks', component: UserviewfeedbackComponent, canActivate: [AuthguardGuard], data: { role: 'USER' }},
  { path: 'user/add/Feedbacks', component: UseraddfeedbackComponent, canActivate: [AuthguardGuard], data: { role: 'USER' }},
  { path: 'user/appliedLoan', component: UserappliedloanComponent, canActivate: [AuthguardGuard], data: { role: 'USER' }}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
