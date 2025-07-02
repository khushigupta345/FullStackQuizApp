
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { StorageService } from './storage.service';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private storageService: StorageService,
    private router: Router
  ) {}

  canActivate(): boolean {
    const user = this.storageService.getUser();
    
    if (user) {
      return true; 
    } else {
      this.router.navigate(['/login']); 
      return false;
    }
  }
}