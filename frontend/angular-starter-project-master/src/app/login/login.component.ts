import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  
loginWithGoogle(): void {

window.location.href = 'http://localhost:8012/oauth2/authorization/google?prompt=consent%20select_account&access_type=offline';
  }
}
