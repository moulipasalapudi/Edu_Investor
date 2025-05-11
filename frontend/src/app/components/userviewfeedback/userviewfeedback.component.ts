import { Component, OnInit } from '@angular/core';
import { AuthHelperService } from '../../helpers/auth-helper.service';
import { FeedbackService } from '../../services/feedback.service';

@Component({
  selector: 'app-userviewfeedback',
  templateUrl: './userviewfeedback.component.html',
  styleUrls: ['./userviewfeedback.component.css']
})
export class UserviewfeedbackComponent implements OnInit {

  feedbacks: any[] = [];  // Explicitly typed array
  delete: any = null;

  constructor(
    private feedbackService: FeedbackService,
    private authHelper: AuthHelperService
  ) {}

  ngOnInit(): void {
    const userIdStr = sessionStorage.getItem('userId');
    const userId = userIdStr ? +userIdStr : null;

    console.log('User ID:', userId);

    if (userId !== null) {
      this.feedbackService.getAllFeedbacksByUserId(userId)
        .subscribe({
          next: (feedbacks) => {
            this.feedbacks = feedbacks;
            console.log('Loaded feedbacks:', this.feedbacks);
          },
          error: (error) => {
            console.error('Failed to load feedbacks:', error);
          }
        });
    } else {
      console.error('No valid user ID found in sessionStorage.');
    }
  }

  confirmDelete(feedback: any) {
    this.delete = feedback;
  }

  deleteFeedback() {
    if (this.delete) {
      // Ideally call API to delete feedback from backend too!
      this.feedbackService.deleteFeedBack(this.delete.id).subscribe({
        next: () => {
          this.feedbacks = this.feedbacks.filter(fb => fb.id !== this.delete.id);
          this.delete = null;
          console.log('Feedback deleted successfully.');
        },
        error: (error) => {
          console.error('Failed to delete feedback:', error);
        }
      });
    }
  }

  cancelDelete() {
    this.delete = null;
  }
}
