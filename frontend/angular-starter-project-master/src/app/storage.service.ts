// import { Injectable } from '@angular/core';
// import { BehaviorSubject } from 'rxjs';

// const USER = 'q_user';

// @Injectable({
//   providedIn: 'root'
// })
// export class StorageService {
//   private static userSubject = new BehaviorSubject<any>(StorageService.getUser());
//   static user$ = StorageService.userSubject.asObservable();

//   static saveUser(user: any): void {
//     window.localStorage.setItem(USER, JSON.stringify(user));
//     StorageService.userSubject.next(user);  // Broadcast user data
//   }

//   static getUser(): any {
//     return JSON.parse(localStorage.getItem(USER) || 'null');
//   }

//   static getUserRole(): string {
//     const user = this.getUser();
//     return user ? user.role : '';
//   }

//   static setUserId(userId: number): void {
//     console.log('Setting userId to:', userId);  // Debugging line
//     localStorage.setItem('userId', userId.toString());
//   }

//   static getUserId(): number | null {
//     const user=this.getUser();

    
//     console.log(user.id);
//     return user? user.id: null;
//   }

//   // static clearUserId(): void {
//   //   console.log('Clearing userId');  // Debugging line
//   //   localStorage.removeItem('userId');
//   // }

//   static isUserLoggedIn(): boolean {
//     return this.getUserRole() === 'USER';
//   }

//   static isAdminLoggedIn(): boolean {
//     return this.getUserRole() === 'ADMIN';
//   }

//   static signOut(): void {
//     window.localStorage.removeItem(USER);
//     StorageService.userSubject.next(null);
//     // this.clearUserId();  // Clear userId during sign out
//   }
// }
// //right 
// import { Injectable } from '@angular/core';

// @Injectable({
//   providedIn: 'root'
// })
// export class StorageService {

//   constructor() {}

//   saveUser(user: any): void {
//     localStorage.setItem('user', JSON.stringify(user));
//   }

//   getUser(): any {
//     const user = localStorage.getItem('user');
//     return user ? JSON.parse(user) : null;
//   }

//   clear(): void {
//     localStorage.removeItem('user');
//   }
// }
// import { Injectable } from '@angular/core';

// @Injectable({
//   providedIn: 'root'
// })
// export class StorageService {
//   static getUserId() {
//     throw new Error('Method not implemented.');
//   }
//   static signOut() {
//       throw new Error('Method not implemented.');
//   }
//   static user$: any;

//   saveUser(user: any) {
//     localStorage.setItem('user', JSON.stringify(user)); // User data save
//   }

//   getUser() {
//     const user = localStorage.getItem('user');
//     return user ? JSON.parse(user) : null; // User data get
//   }

//   getUserId() {
//     const user = this.getUser();
//     return user ? user.id : null; // Only User ID return
//   }

//   clearUser() {
//     localStorage.removeItem('user'); // Logout par clear karna
//   }
// }
// import { Injectable } from '@angular/core';

// @Injectable({
//   providedIn: 'root'
// })
// export class StorageService {
  
//   static signOut() {
//     throw new Error('Method not implemented.');
//   }

//   // User save karo localStorage me
//   saveUser(user: any): void {
//     localStorage.setItem('user', JSON.stringify(user));
//   }

//   // User data get karo
//   getUser(): any {
//     const user = localStorage.getItem('user');
//     return user ? JSON.parse(user) : null;
//   }

//   // User ID get karo
//   getUserId(): number | null {
//     const user = this.getUser();
//     return user ? user.id : null;
//   }

//   // User role get karo
//   getUserRole(): string {
//     const user = this.getUser();
//     return user ? user.role : '';
//   }

//   // Check user login
//   isUserLoggedIn(): boolean {
//     return this.getUserRole() === 'USER';
//   }

//   // Check admin login
//   isAdminLoggedIn(): boolean {
//     return this.getUserRole() === 'ADMIN';
//   }

//   // User data clear (logout ke liye)
//   clearUser(): void {
//     localStorage.removeItem('user');
//   }
// }
// import { Injectable } from '@angular/core';
// import { BehaviorSubject } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class StorageService {
//   saveToken(token: any) {
//     throw new Error('Method not implemented.');
//   }
//   private userSubject = new BehaviorSubject<any>(this.getUser());
//   user$ = this.userSubject.asObservable();

//   saveUser(user: any): void {
//     localStorage.setItem('user', JSON.stringify(user));
//     this.userSubject.next(user);
//   }

//   getUser(): any {
//     const user = localStorage.getItem('user');
//     return user ? JSON.parse(user) : null;
//   }

//   getUserId(): number | null {
//     const user = this.getUser();
//     return user ? user.id : null;
//   }

//   signOut(): void {
//     localStorage.removeItem('user');
//     this.userSubject.next(null);
//   }

//   getUserRole(): string {
//     const user = this.getUser();
//     return user ? user.role : '';
//   }

//   isUserLoggedIn(): boolean {
//     return this.getUserRole() === 'USER';
//   }

//   isAdminLoggedIn(): boolean {
//     return this.getUserRole() === 'ADMIN';
//   }
// }
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private userSubject = new BehaviorSubject<any>(this.getUser());
  user$ = this.userSubject.asObservable();

  // Save JWT token
  saveToken(token: string): void {
    localStorage.setItem('jwtToken', token);
  }

  // Get JWT token
  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  // Save user object (after decoding from token or backend response)
  saveUser(user: any): void {
    localStorage.setItem('user', JSON.stringify(user));
    this.userSubject.next(user);
  }

  // Get entire user object
  getUser(): any {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  // Get specific fields
  getUserId(): string | null {
    const user = this.getUser();
    return user?.id || null;
  }
  // getUserId(): string | null {
  //   const user = this.getUser();
  //   return user?.userId || null;
  // }
  
  getUserEmail(): string | null {
    const user = this.getUser();
    return user?.email || null;
  }

  getUserRole(): string | null {
    const user = this.getUser();
    return user?.role || null;
  }

  // Check role
  isUserLoggedIn(): boolean {
    return this.getUserRole() === 'USER';
  }

  isAdminLoggedIn(): boolean {
    return this.getUserRole() === 'ADMIN';
  }

  // Sign out
  signOut(): void {
    localStorage.removeItem('user');
    localStorage.removeItem('jwtToken');
    this.userSubject.next(null);
  }
}