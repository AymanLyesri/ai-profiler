import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject, Subscription, retry } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService
{
  private URL = environment.URL + "api/";

  public isLoggedIn$ = new Subject<boolean>();

  public user: User;

  constructor(private http: HttpClient) { }

  // Login method with HTTP request
  login(username: string, password: string)
  {
    const body = { username: username, password: password };

    return this.http.post<any>(this.URL + 'login', body).subscribe({
      next: (response) =>
      {
        console.log('Login response:', response);
        if (response.status === 200) {
          localStorage.setItem('username', username);
          localStorage.setItem('password', password);
          this.user = response.data;
          console.log('User:', this.user);

          this.isLoggedIn$.next(true);
        }
        else {
          this.isLoggedIn$.next(false);
        }
      },
      error: (error) =>
      {
        console.error('Login error:', error);
        this.isLoggedIn$.next(false);
      }
    });
  }


  // Check if the user is logged in
  isLoggedIn()
  {
    // Check if username and password are stored in localStorage
    const username = localStorage.getItem('username');
    const password = localStorage.getItem('password');

    // If both are stored, the user is logged in
    this.login(username, password);

    // Return the observable
    return this.isLoggedIn$.asObservable();
  }

  // Logout
  logout()
  {
    // Clear username and password from localStorage
    localStorage.removeItem('username');
    localStorage.removeItem('password');
    this.isLoggedIn$.next(false);
  }
}
