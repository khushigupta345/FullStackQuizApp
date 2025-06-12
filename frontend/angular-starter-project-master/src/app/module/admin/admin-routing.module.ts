import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateTestComponent } from './components/create-test/create-test.component';
import { AddQuestionQuizComponent } from './components/add-question-quiz/add-question-quiz.component';
import { ViewQuestionComponent } from './components/view-question/view-question.component';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { QuizService } from 'src/app/auth/signup/quiz.service';
import { ViewResultQuizComponent } from './view-result-quiz/view-result-quiz.component';
import { EditquizComponent } from './components/editquiz/editquiz.component';




const routes: Routes = [
  // { path:'',redirectTo:'/dashboard'},
    { path:"dashboard",component:DashboardComponent},
  
   { path:"create-test",component:CreateTestComponent},
   { path:"add-question/:id",component:AddQuestionQuizComponent},
   { path:"view-test/:id",component:ViewQuestionComponent}
   ,{ path:"view-test-result",component:ViewResultQuizComponent}
   ,{ path:"edit-quiz/:id",component:EditquizComponent}
   
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { 
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

  // Corrected time formatting function
  getFormattedTime(time: number): string {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes} minutes ${seconds} seconds`;
  }

  submitQuiz() {
    // Implement your logic for "View Test" or "Add Question"
  }
}


