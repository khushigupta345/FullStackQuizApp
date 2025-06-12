import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-login-success',
  standalone: true,
  imports: [],
  templateUrl: './login-success.component.html',
  styleUrl: './login-success.component.css'
})
export class LoginSuccessComponent {

  constructor(private route: ActivatedRoute, private router: Router,private storageService:StorageService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const token = params['token'];
      if (token) {
        localStorage.setItem('jwtToken', token);

        // Decode token to extract user data
        const payload = JSON.parse(atob(token.split('.')[1]));
        const user = {
          email: payload.sub,
          role: payload.role,
          // id: payload.userId
         id: payload.id
        };

 console.log(user.id);
        localStorage.setItem('user', JSON.stringify(user));
this.storageService.saveUser(user);


        // this.router.navigate(['/dashboard']);
      } else {
        // this.router.navigate(['/login']);
      }
    });
  }
}
