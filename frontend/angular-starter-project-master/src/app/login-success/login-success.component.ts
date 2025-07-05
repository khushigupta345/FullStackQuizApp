import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StorageService } from '../storage.service';

@Component({
  selector: 'app-login-success',
  standalone: true,
  imports: [],
  templateUrl: './login-success.component.html',
  styleUrl: './login-success.component.css'
})
export class LoginSuccessComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private storageService: StorageService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const token = params['token'];

      if (token) {
        // Save token
        localStorage.setItem('jwtToken', token);

        try {
          // Decode JWT payload
          const payload = JSON.parse(atob(token.split('.')[1]));

          const user = {
            email: payload.sub,
            role: payload.role,
            id: payload.id
          };

          // Save user in localStorage and service
          localStorage.setItem('user', JSON.stringify(user));
          this.storageService.saveUser(user);

          console.log('User role:', user.role);

          // Route based on role after slight delay
          setTimeout(() => {
            if (user.role === 'ADMIN') {
              this.router.navigate(['/admin/dashboard']);
            } else if (user.role === 'USER') {
              this.router.navigate(['/user/dashboard']);
            } else {
              this.router.navigate(['/login']);
            }
          }, 100);

        } catch (e) {
          console.error("Invalid token format", e);
          this.router.navigate(['/login']);
        }
      } else {
        // No token in URL
        this.router.navigate(['/login']);
      }
    });
  }
}
