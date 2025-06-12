// import { Component } from '@angular/core';
// import { Router, RouterOutlet } from '@angular/router';
// import { SharedModule } from './module/shared/shared.module';
// import { StorageService } from './storage.service';


// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   // imports:[RouterOutlet,SharedModule],
//   styleUrls: ['./app.component.css']
// })

// export class AppComponent {
//   title = 'angular-starter';
//   isUserLoggedIn:boolean=StorageService.isUserLoggedIn();
//   isAdminLoggedIn:boolean=StorageService.isAdminLoggedIn();
//   constructor(private router:Router){}
// ngOnInit(){
//   this.router.events.subscribe(event=>{
//     this.isUserLoggedIn=StorageService.isUserLoggedIn();
//     this.isAdminLoggedIn=StorageService.isAdminLoggedIn();
//   })
// }
// logout(){
//   StorageService.signOut();
//   this.router.navigateByUrl('login');

// }
// }
//right code
// import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
// import { StorageService } from './storage.service';

// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   styleUrls: ['./app.component.css']
// })
// export class AppComponent implements OnInit {
//   title(title: any) {
//     throw new Error('Method not implemented.');
//   }
//   isUserLoggedIn: boolean = false;
//   isAdminLoggedIn: boolean = false;

//   constructor(private router: Router) {}

//   ngOnInit() {
//     StorageService.user$.subscribe(user => {
//       this.isUserLoggedIn = user?.role === 'USER';
//       this.isAdminLoggedIn = user?.role === 'ADMIN';
//     });
//   }

//   logout() {
//     StorageService.signOut();
//     this.router.navigateByUrl('/login');
//   }
// }
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from './storage.service';
import { OAuthService } from 'angular-oauth2-oidc';
// import { authConfig } from './auth.confg';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
//   title = 'angular-starter';
// export class AppComponent implements OnInit {
//   title(title: any) {
//     throw new Error('Method not implemented.');
//   }
//   isUserLoggedIn: boolean = false;
//   isAdminLoggedIn: boolean = false;

//   constructor(private router: Router) {}

  export class AppComponent implements OnInit {
   title = 'angular-starter';
    isUserLoggedIn = false;
    isAdminLoggedIn = false;
  
    constructor(private router: Router, private storageService: StorageService,private oauthService: OAuthService ) {}
    ngOnInit() {
//       this.oauthService.configure(authConfig);
// this.oauthService.loadDiscoveryDocumentAndTryLogin();

      this.storageService.user$.subscribe(user => {
        this.isUserLoggedIn = user?.role === 'USER';
        this.isAdminLoggedIn = user?.role === 'ADMIN';
      });
    }
  
    logout() {
      this.storageService.signOut();
      this.router.navigateByUrl('/login');
    }
  }
  // logout() {
  //   StorageService.signOut();
  //   this.router.navigateByUrl('/login');
  // }
//}