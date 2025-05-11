import { Component, OnInit } from '@angular/core';
import { FeedbackService } from '../../services/feedback.service';
import { AuthHelperService } from '../../helpers/auth-helper.service';
import { Feedback } from '../../models/feedback.model';

 
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
      userId: +(sessionStorage.getItem('userId') || '0'),
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