import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StartquizComponent } from './startquiz/startquiz.component';
import { ViewMyResultComponent } from './view-my-result/view-my-result.component';


@NgModule({
  declarations: [
    DashboardComponent,
    StartquizComponent,
    ViewMyResultComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,

    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
