import { Component, OnInit } from '@angular/core';
import { AuthHelperService } from 'src/app/helpers/auth-helper.service';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';
 
@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {
  feedbackText: string = '';
  showPopup: boolean = false;
  errorMessage: string = '';
 
  constructor(private feedbackService: FeedbackService, private authHelper: AuthHelperService) {}
 
  submitFeedback() {
    if (!this.feedbackText.trim()) {
      this.errorMessage = "Feedback is required";
      return;
    }
 
    const feedback: Feedback = {
      userId: +sessionStorage.getItem('userId'),
      feedbackText: this.feedbackText,
      date: new Date()
    };
 
    this.feedbackService.sendFeedback(feedback).subscribe(() => {
      this.showPopup = true;
    });
  }
 
  closePopup() {
    this.showPopup = false;
    this.feedbackText = '';
  }
 
  ngOnInit(): void {}
}