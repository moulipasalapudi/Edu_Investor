import { Component, OnInit } from '@angular/core';
import { AuthHelperService } from 'src/app/helpers/auth-helper.service';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';
 
@Component({
  selector: 'app-userviewfeedback',
  templateUrl: './userviewfeedback.component.html',
  styleUrls: ['./userviewfeedback.component.css']
})
export class UserviewfeedbackComponent implements OnInit {
  feedbacks = [];
 
  delete: any = null;
 
  constructor(private feedbackService:FeedbackService,private authHelper:AuthHelperService) { }
 
  ngOnInit(): void {
    console.log(this.authHelper.getUserId());
    // // const l = 1;
 
    // const decodedToken: any = this.authHelper.decodeToken(response.token);
    // this.authHelper.setUserId(Number(decodedToken?.userId))
    console.log(+sessionStorage.getItem('userId'));
   
   this.feedbackService.getAllFeedbacksByUserId(+sessionStorage.getItem('userId'))
     .subscribe(feedbacks => this.feedbacks = feedbacks);
     console.log(this.feedbacks);
  }
 
  confirmDelete(feedback: any) {
    this.delete = feedback;
  }
 
  deleteFeedback() {
    if (this.delete) {
      this.feedbacks = this.feedbacks.filter(fb => fb.id !== this.delete.id);
      this.delete = null;
    }
  }
 
  cancelDelete() {
    this.delete = null;
  }
 
}