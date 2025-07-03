import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateTestComponent } from './components/create-test/create-test.component';
import { AddQuestionQuizComponent } from './components/add-question-quiz/add-question-quiz.component';
import { ViewQuestionComponent } from './components/view-question/view-question.component';
import { ViewResultQuizComponent } from './view-result-quiz/view-result-quiz.component';
import { EditquizComponent } from './components/editquiz/editquiz.component';

const routes: Routes = [
  { path: "dashboard", component: DashboardComponent },
  { path: "create-test", component: CreateTestComponent },
  { path: "add-question/:id", component: AddQuestionQuizComponent },
  { path: "view-test/:id", component: ViewQuestionComponent },
  { path: "view-test-result", component: ViewResultQuizComponent },
  { path: "edit-quiz/:id", component: EditquizComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
