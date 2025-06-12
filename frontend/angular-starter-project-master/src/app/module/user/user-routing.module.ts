import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StartquizComponent } from './startquiz/startquiz.component';
import { ViewQuestionComponent } from '../admin/components/view-question/view-question.component';
import { ViewMyResultComponent } from './view-my-result/view-my-result.component';


const routes: Routes = [
  
 { path:"dashboard",component:DashboardComponent},
 { path:"take-test/:id",component:StartquizComponent},
   { path:"view-test/:id",component:ViewQuestionComponent},
   { path:"view-test-results",component:ViewMyResultComponent}
   
 ];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
