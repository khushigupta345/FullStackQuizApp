import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './login/login.component';
import { QuizService } from './auth/signup/quiz.service';
import { OAuthModule, OAuthService } from 'angular-oauth2-oidc';
import { AuthInterceptor } from './auth.interceptor';
import { HomeComponent } from './home/home.component';
import { TodoComponent } from './todo/todo.component';

registerLocaleData(en);
@NgModule({
  // providers:[QuizService],
  declarations: [
  
    AppComponent,
    TodoComponent,
    SignupComponent,
    LoginComponent,
    HomeComponent,

  ],
  imports: [
  
    // QuizService,
    NzLayoutModule,
    BrowserModule,
    
    ReactiveFormsModule,
  RouterOutlet,

  RouterLink,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    OAuthModule.forRoot(),
    BrowserAnimationsModule
  ],
  // providers: [QuizService], // ✅ Ensure service is provided

  providers: [
    // 👈 Yahan register karo
   { provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true},


    { provide: NZ_I18N, useValue: en_US }
    ,QuizService,OAuthService
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }

// import { NgModule } from '@angular/core';
// import { BrowserModule } from '@angular/platform-browser';

// import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { HttpClientModule } from '@angular/common/http';
// import { RouterLink, RouterOutlet } from '@angular/router';
// import { NZ_I18N } from 'ng-zorro-antd/i18n';
// import { en_US } from 'ng-zorro-antd/i18n';
// import { registerLocaleData } from '@angular/common';
// import en from '@angular/common/locales/en';
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { NzLayoutModule } from 'ng-zorro-antd/layout';
// import { SignupComponent } from './auth/signup/signup.component';
// import { LoginComponent } from './login/login.component';
// import { QuizService } from './auth/signup/quiz.service';
// import { OAuthService } from 'angular-oauth2-oidc';

// registerLocaleData(en);
// @NgModule({
//   // providers:[QuizService],
//   declarations: [
   
//     AppComponent,
//     SignupComponent,
//     LoginComponent
//   ],
//   imports: [
//     // QuizService,
//     NzLayoutModule,
//     BrowserModule,
//     ReactiveFormsModule,
//   RouterOutlet,

//   RouterLink,
//     AppRoutingModule,
//     FormsModule,
//     ReactiveFormsModule,
//     HttpClientModule,
//     BrowserAnimationsModule
//   ],
//   // providers: [QuizService], // ✅ Ensure service is provided

//   providers: [
//     // 👈 Yahan register karo

//     { provide: NZ_I18N, useValue: en_US }
//     ,QuizService
//   ],
//   bootstrap: [AppComponent]
// })
// export class AppModule { }
