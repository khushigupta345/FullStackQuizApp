
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from './storage.service';
import { OAuthService } from 'angular-oauth2-oidc';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

  export class AppComponent implements OnInit {
   title = 'angular-starter';
    isUserLoggedIn = false;
    isAdminLoggedIn = false;
  
    constructor(private router: Router, private storageService: StorageService,private oauthService: OAuthService ) {}
    ngOnInit() {

      this.storageService.user$.subscribe(user => {
        this.isUserLoggedIn = user?.role === 'USER';
        this.isAdminLoggedIn = user?.role === 'ADMIN';
      });
    }
  
    // logout() {
    //   this.storageService.signOut();
    //   this.router.navigateByUrl('/login');
    // }
    logout(): void {
  this.storageService.signOut(); // local storage clear
  this.router.navigate(['/login']); // redirect to login page
}
  }
  