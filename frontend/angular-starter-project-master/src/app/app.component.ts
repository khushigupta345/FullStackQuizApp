import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isUserLoggedIn = false;
  isAdminLoggedIn = false;
  menuOpen = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    const role = localStorage.getItem('user-role');
    this.isUserLoggedIn = role === 'USER';
    this.isAdminLoggedIn = role === 'ADMIN';
  }

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }

  logout(): void {
    localStorage.clear();
    this.isUserLoggedIn = false;
    this.isAdminLoggedIn = false;
    this.menuOpen = false;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/home']);
    });
  }
}
