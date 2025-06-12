import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateTestComponent } from './components/create-test/create-test.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddQuestionQuizComponent } from './components/add-question-quiz/add-question-quiz.component';
import { ViewQuestionComponent } from './components/view-question/view-question.component';
import { ViewResultQuizComponent } from './view-result-quiz/view-result-quiz.component';
import { EditquizComponent } from './components/editquiz/editquiz.component';


@NgModule({
  declarations: [
    DashboardComponent,
    CreateTestComponent,
    AddQuestionQuizComponent,
    ViewQuestionComponent,
    ViewResultQuizComponent,
    EditquizComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }


