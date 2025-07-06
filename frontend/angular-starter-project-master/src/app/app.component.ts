import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from './storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isUserLoggedIn = false;
  isAdminLoggedIn = false;
  menuOpen = false;

  constructor(private router: Router, private storageService: StorageService) {}

  ngOnInit(): void {
    this.updateUserRole(this.storageService.getUserRole());

    this.storageService.user$.subscribe(user => {
      this.updateUserRole(user?.role || null);
    });
  }

  updateUserRole(role: string | null): void {
    this.isUserLoggedIn = role === 'USER';
    this.isAdminLoggedIn = role === 'ADMIN';
  }

  toggleMenu(): void {
    this.menuOpen = !this.menuOpen;
  }

  logout(): void {
    this.storageService.signOut();
    this.updateUserRole(null);
    this.menuOpen = false;
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/home']);
    });
  }
}
