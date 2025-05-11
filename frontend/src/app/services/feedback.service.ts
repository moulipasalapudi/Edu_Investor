import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Feedback } from '../models/feedback.model';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { AuthHelperService } from '../helpers/auth-helper.service';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient, private authHelper: AuthHelperService) { }

  sendFeedback(feedback: Feedback): Observable<Feedback> {
    return this.http.post<Feedback>(`${this.apiUrl}/feedback`, feedback);
  }

  getAllFeedbacksByUserId( userId: number): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.apiUrl}/feedback/user/${userId}`);
  }

  deleteFeedBack(feedbackId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/feedback/${feedbackId}`);
  }

  getFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.apiUrl}/feedback`);
  }
  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/user/${userId}`);
  }
}
