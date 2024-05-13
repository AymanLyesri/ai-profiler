import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject, Subscription, catchError, map, of, retry } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
// export class AuthService
// {
//   private URL = environment.URL + "api/";

//   public isLoggedIn$ = new Subject<boolean>();

//   public user: User;

//   constructor(private http: HttpClient) { }

//   login(username: string, password: string): Observable<boolean>
//   {
//     const body = { username: username, password: password };

//     return this.http.post<any>(this.URL + 'login', body).pipe(
//       map(response =>
//       {
//         console.log('Login response:', response);
//         if (response.status === 200) {
//           localStorage.setItem('username', username);
//           localStorage.setItem('password', password);
//           this.user = response.data;
//           console.log('User:', this.user);
//           return true;
//         } else {
//           return false;
//         }
//       })
//     );
//   }


//   // Check if the user is logged in
//   isLoggedIn()
//   {
//     // Check if username and password are stored in localStorage
//     const username = localStorage.getItem('username');
//     const password = localStorage.getItem('password');

//     // If both are stored, the user is logged in
//     this.login(username, password).subscribe(
//       (response) =>
//       {
//         console.log('Is logged in:', response);

//         this.isLoggedIn$.next(response);
//       }
//     );
//     // Return the observable
//     return this.isLoggedIn$.asObservable();
//   }

//   // Logout
//   logout()
//   {
//     // Clear username and password from localStorage
//     localStorage.removeItem('username');
//     localStorage.removeItem('password');
//     this.isLoggedIn$.next(false);
//   }
// }

export class AuthService
{
  private readonly JWT_TOKEN = 'JWT_TOKEN';
  private readonly REFRESH_TOKEN = 'REFRESH_TOKEN';
  private jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) { }

  login(credentials: { username: string, password: string })
  {
    return this.http.post<any>('/api/login', credentials)
      .subscribe(response =>
      {
        this.storeTokens(response.jwtToken, response.refreshToken);
      });
  }

  private storeTokens(jwt: string, refreshToken: string)
  {
    localStorage.setItem(this.JWT_TOKEN, jwt);
    localStorage.setItem(this.REFRESH_TOKEN, refreshToken);
  }

  logout()
  {
    localStorage.removeItem(this.JWT_TOKEN);
    localStorage.removeItem(this.REFRESH_TOKEN);
  }

  isLoggedIn()
  {
    const jwt = localStorage.getItem(this.JWT_TOKEN);
    return jwt && !this.jwtHelper.isTokenExpired(jwt);
  }

  getJwtToken()
  {
    return localStorage.getItem(this.JWT_TOKEN);
  }
}