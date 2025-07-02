import { Component } from '@angular/core';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { QuizService } from 'src/app/auth/signup/quiz.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
quizzes=[];
  constructor(
    private notification: NzNotificationService,
    private quizService: QuizService
  ) {}

  ngOnInit() {
    this.getQuizzes();
  }

  getQuizzes() {
    this.quizService.getAllQuiz().subscribe(
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


}
