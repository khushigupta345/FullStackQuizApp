

import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  quizzes = [];  

  constructor(
    private router:Router,
    private notification: NzNotificationService,
    private quizService: QuizService,private message:NzMessageService
  ) {}

  ngOnInit() {
    this.getQuizzes();
    

  }

  getQuizzes() {
    // this.quizService.getAllQuiz().subscribe(
      this.quizService.getAllQuizAdmin().subscribe(
      (res) => {
        this.quizzes = res;
      },
      (error) => {
        this.notification.error(
          'ERROR',
          'Something went wrong',
          { nzDuration: 54000 }
        );
      }
    );
  }

 
  getFormattedTime(time: number): string {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes} minutes ${seconds} seconds`;
  }



deleteQuiz(id: number) {
  if (confirm("Are you sure you want to delete this quiz?")) {
    this.quizService.deleteById(id).subscribe({
      next: () => {
        alert("Quiz deleted.");
        this.getQuizzes(); // refresh
      },
      error: (err) => {
        console.error(err);
        alert("Failed to delete quiz.");
      }
    });
  }
}



  
