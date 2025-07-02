
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

 
  getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  saveUser(user: any): void {
    localStorage.setItem('user', JSON.stringify(user));
    this.userSubject.next(user);
  }

  getUser(): any {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

 
  getUserId(): string | null {
    const user = this.getUser();
    return user?.id || null;
  }
  
  
  getUserEmail(): string | null {
    const user = this.getUser();
    return user?.email || null;
  }

  getUserRole(): string | null {
    const user = this.getUser();
    return user?.role || null;
  }

  
  isUserLoggedIn(): boolean {
    return this.getUserRole() === 'USER';
  }

  isAdminLoggedIn(): boolean {
    return this.getUserRole() === 'ADMIN';
  }

  // signOut(): void {
  //   localStorage.removeItem('user');
  //   localStorage.removeItem('jwtToken');
  //   localStorage.clear();
   
  //   this.userSubject.next(null);
  // }
  signOut(): void {
  localStorage.removeItem('user');
  localStorage.removeItem('jwtToken');
  localStorage.clear();
  this.userSubject.next(null);
}
}