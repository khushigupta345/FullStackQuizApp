
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './login/login.component';
import { LoginSuccessComponent } from './login-success/login-success.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth.guard';


const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: "register", component: SignupComponent },
 
  { path: "login-success", component: LoginSuccessComponent }, 
  { path: "login", component: LoginComponent },
  { path: "user", loadChildren: () => import('./module/user/user.module').then(m => m.UserModule),canActivate: [AuthGuard] },
  { path: "admin", loadChildren: () => import('./module/admin/admin.module').then(m => m.AdminModule),canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }