import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isUserLoggedIn = false;
  isAdminLoggedIn = false;
  menuOpen = false;

  ngOnInit(): void {
    const role = localStorage.getItem('user-role');
    this.isUserLoggedIn = role === 'USER';
    this.isAdminLoggedIn = role === 'ADMIN';
  }

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }
/*
  logout(): void {
    localStorage.clear();
    window.location.href = '/login';
  }*/

logout() {
  localStorage.clear();
  this.isUserLoggedIn = false;
  this.isAdminLoggedIn = false;
  this.menuOpen = false;
  this.router.navigate(['/home']); 
}
  
  
}
