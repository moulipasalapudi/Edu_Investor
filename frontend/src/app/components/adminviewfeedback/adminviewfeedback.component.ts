import { Component, OnInit, OnDestroy } from '@angular/core';

import { Subscription } from 'rxjs';
import { FeedbackService } from '../../services/feedback.service';
import { Feedback } from '../../models/feedback.model';
 
@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
})
export class AdminviewfeedbackComponent implements OnInit {
  feedbacks: Feedback[] = [];
  showConfirmation: boolean = false;
  selectedFeedback: any | null = null;
  private subscription: Subscription | null = null;
 
  constructor(private feedbackService: FeedbackService) {}
 
  ngOnInit(): void {
    this.getAllFeedbacks();
  }
 
  getAllFeedbacks(): void {
    this.feedbackService.getFeedbacks().subscribe(feedback => {
      console.log(feedback);
      this.feedbacks = feedback;
    });
  }
 
  showProfile(feedback: any): void {
    this.selectedFeedback = feedback;
  }
 
  closeProfile(): void {
    this.selectedFeedback = null;
  }
 
  confirmCloseProfile(confirm: boolean): void {
    if (confirm) {
      this.selectedFeedback = null;
    }
    this.showConfirmation = false;
  }
}