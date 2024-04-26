import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service'; // Your authentication service
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate
{

    constructor(private authService: AuthService, private router: Router) { }

    canActivate(): Observable<boolean>
    {
        return this.authService.isLoggedIn().pipe(
            map((isLoggedIn: boolean) =>
            {

                if (!isLoggedIn) {
                    console.log('User is not logged in:', isLoggedIn);
                    this.router.navigate(['/login']);
                    return false;
                }
                console.log('User is logged in:', isLoggedIn);
                return true;
            })
        );
    }
}
