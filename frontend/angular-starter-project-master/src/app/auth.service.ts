// import { Injectable } from '@angular/core';
// import { OAuthService } from 'angular-oauth2-oidc';
// import { Router } from '@angular/router';
// import { StorageService } from './storage.service';
// import { Observable } from 'rxjs';
// import { jwtDecode } from 'jwt-decode';
// import { authConfig } from './auth.confg';

// @Injectable({
//   providedIn: 'root',
// })
// export class AuthService {
//   constructor(
    
//     private oauthService: OAuthService, // ✅ lowercase name
//     private router: Router,
//     private storageService: StorageService
//    ) { 
//   // this.oauthService.configure(authConfig);
//   //   this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
//   //     if (this.oauthService.hasValidAccessToken()) {
//   //       const claims: any = this.oauthService.getIdentityClaims();
//   //       const email = claims?.email;

//   //       fetch(`/api/auth/oauth2/success?email=${email}`)
//   //         .then((res) => res.json())
//   //         .then((data) => {
//   //           const token = data.token;
//   //           localStorage.setItem('jwt_token', token);
//   //           const decode: any = jwtDecode(token);
//   //           const role = decode.role;

//   //           const user = {
//   //             email: email,
//   //             role: role,
//   //             token: token,
//   //           };

//   //           this.storageService.saveUser(user);

//   //           if (this.storageService.isAdminLoggedIn()) {
//   //             this.router.navigateByUrl('admin/dashboard');
//   //           } else {
//   //             this.router.navigateByUrl('user/dashboard');
//   //           }
//   //         });
//   //     }
//   //   });
//   }
//   initAuth() {
//     this.oauthService.configure(authConfig);
//     this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
//       if (this.oauthService.hasValidAccessToken()) {
//         const claims: any = this.oauthService.getIdentityClaims();
//         const email = claims?.email;
  
//         fetch(`/api/auth/oauth2/success?email=${email}`)
//           .then((res) => res.json())
//           .then((data) => {
//             const token = data.token;
//             localStorage.setItem('jwt_token', token);
//             const decode: any = jwtDecode(token);
//             const role = decode.role;
  
//             const user = { email, role, token };
//             this.storageService.saveUser(user);
  
//             if (this.storageService.isAdminLoggedIn()) {
//               this.router.navigateByUrl('admin/dashboard');
//             } else {
//               this.router.navigateByUrl('user/dashboard');
//             }
//           });
//       }
//     });
//   }
//   loginWithGoogle(): Observable<any> {
//     return new Observable((observer) => {
//       this.oauthService.initLoginFlow();

//       this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
//         if (this.oauthService.hasValidAccessToken()) {
//           const claims: any = this.oauthService.getIdentityClaims();
//           const email = claims?.email;

//           fetch(`/api/auth/oauth2/success?email=${email}`)
//             .then((res) => res.json())
//             .then((data) => {
//               const decoded: any = jwtDecode(data.token);
//               observer.next({
//                 token: data.token,
//                 email: email,
//                 role: decoded.role,
//               });
//               observer.complete();
//             })
//             .catch((err) => observer.error(err));
//         } else {
//           observer.error("Invalid access token");
//         }
//       });
//     });
//   }

//   logout() {
//     this.oauthService.logOut();
//     localStorage.removeItem('jwt_token');
//   }
// }
// // import { Injectable } from '@angular/core';
// // import { OAuthService } from 'angular-oauth2-oidc';

// // import { Router } from '@angular/router';
// // import { StorageService } from './storage.service';
// // import { Observable } from 'rxjs';
// // import {jwtDecode} from 'jwt-decode';
// // import { authConfig } from './auth.confg';

// // @Injectable({
// //   providedIn: 'root',
// // })
// // export class AuthService {
// //   constructor(
// //     private oauthService: OAuthService,
// //     private router: Router,
// //     private storageService: StorageService
// //   ) {
// //     this.oauthService.configure(authConfig);
// //     this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
// //       if (this.oauthService.hasValidAccessToken()) {
// //         const claims: any = this.oauthService.getIdentityClaims();
// //         const email = claims?.email;

// //         // If already logged in, directly fetch backend token
// //         fetch(`/api/auth/oauth2/success?email=${email}`)
// //           .then((res) => res.json())
// //           .then((data) => {
// //             const token = data.token;
// //             localStorage.setItem('jwt_token', token);
// //             const decode:any=jwtDecode(token);
// //             //             // const decodedToken: any = jwt_decode(token);
// //                         const role = decode.role;
// //             //const decodedToken: any = jwt_decode(token);
          
// //             const user = {
// //               email: email,
// //               role: role,
// //               token: token,
// //             };

// //             this.storageService.saveUser(user);

// //             if (this.storageService.isAdminLoggedIn()) {
// //               this.router.navigateByUrl('admin/dashboard');
// //             } else {
// //               this.router.navigateByUrl('user/dashboard');
// //             }
// //           });
// //       }
// //     });
// //   }

// //   loginWithGoogle(): Observable<any> {
// //     return new Observable((observer) => {
// //       this.oauthService.initLoginFlow();

// //       this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
// //         if (this.oauthService.hasValidAccessToken()) {
// //           const claims: any = this.oauthService.getIdentityClaims();
// //           const email = claims?.email;

// //           fetch(`/api/auth/oauth2/success?email=${email}`)
// //             .then((res) => res.json())
// //             .then((data) => {
// //               const decoded: any = jwtDecode(data.token);
// //               observer.next({
// //                 token: data.token,
// //                 email: email,
// //                 role: decoded.role,
// //               });
// //               observer.complete();
// //             })
// //             .catch((err) => observer.error(err));
// //         } else {
// //           observer.error("Invalid access token");
// //         }
// //       });
// //     });
// //   }

// //   logout() {
// //     this.oauthService.logOut();
// //     localStorage.removeItem('jwt_token');
// //   }
// // }
// // import { Injectable } from '@angular/core';
// // import { OAuthService } from 'angular-oauth2-oidc';

// // import {jwtDecode} from 'jwt-decode';
// // import { Router } from '@angular/router';
// // import { StorageService } from './storage.service'; // 👈 tumhara service
// // import { authConfig } from './auth.confg';

// // @Injectable({
// //   providedIn: 'root',
// // })
// // export class AuthService {
// //   loginWithGoogle() {
// //     throw new Error('Method not implemented.');
// //   }
// //   constructor(
// //     private oauthService: OAuthService,
// //     private router: Router,
// //     private storageService: StorageService
// //   ) {
// //     this.oauthService.configure(authConfig);
// //     this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
// //       if (this.oauthService.hasValidAccessToken()) {
// //         const claims: any = this.oauthService.getIdentityClaims();
// //         const email = claims?.email;

// //         // 👇 Call backend to get JWT with role
// //         fetch(`/api/auth/oauth2/success?email=${email}`)
// //           .then((res) => res.json())
// //           .then((data) => {
// //             const token = data.token;
// //             localStorage.setItem('jwt_token', token);

// //             // ✅ decode token and store user info
// //             const decode:any=jwtDecode(token);
// //             // const decodedToken: any = jwt_decode(token);
// //             const role = decode.role;

// //             const user = {
// //               email: email,
// //               role: role,
// //               token: token,
// //             };

// //             this.storageService.saveUser(user);

// //             // ✅ Role-based navigation
// //             if (this.storageService.isAdminLoggedIn()) {
// //               this.router.navigateByUrl('admin/dashboard');
// //             } else {
// //               this.router.navigateByUrl('user/dashboard');
// //             }
// //           });
// //       }
// //     });
// //   }

// //   login() {
// //     this.oauthService.initLoginFlow();
// //   }

// //   logout() {
// //     this.oauthService.logOut();
// //     localStorage.removeItem('jwt_token');
// //   }
// // }

// // // import { Injectable } from '@angular/core';

// // // @Injectable({
// // //   providedIn: 'root'
// // // })
// // // export class AuthService {
// // //   auth = getAuth();

// // //   loginWithGoogle() {
// // //     signInWithPopu(this.auth, new GoogleAuthProvider())
// // //       .then(result => {
// // //         result.user.getIdToken().then(token => {
// // //           localStorage.setItem("token", token);
// // //           window.location.href = "http://localhost:8012/oauth2/authorization/google";
// // //         });
// // //       })
// // //       .catch(error => console.error("Login Error:", error));
// // //   }

// // //   logout() {
// // //     this.auth.signOut();
// // //     localStorage.removeItem("token");
// // //   }
  
// // // }
// // import { Injectable } from '@angular/core';

// // import { HttpClient } from '@angular/common/http';

// // @Injectable({
// //   providedIn: 'root'
// // })










// // export class AuthService {
// //   constructor(private oauthService: OAuthService, private http: HttpClient) {}

// //   loginWithGoogle() {
// //     this.oauthService.initImplicitFlow();
// //   }

// //   getGoogleUser() {
// //     return this.http.get('/auth/google-success');
// //   }
// // }