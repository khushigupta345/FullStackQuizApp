import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  loginWithGoogle(): void {
    window.location.href = 'http://localhost:8012/oauth2/authorization/google';
  }
}
// import { Component } from '@angular/core';
// import { AuthService } from '../auth.service';
// import { StorageService } from '../storage.service';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
// })
// export class LoginComponent {
//   constructor(private authService: AuthService, private storage: StorageService) {}

//   login(){
//     this.authService.loginWithGoogle().subscribe({
//       next: (data: any) => {
//         this.storage.saveToken(data.token);
//         this.storage.saveUser({ email: data.email, role: data.role });
//         alert('Login successful!');
//       },
//       error: (err) => {
//         alert('Login failed');
//       }
//     });
//   }
// }
