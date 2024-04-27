import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { time } from 'console';
import { timeout } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit
{

  public form: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router) { }

  ngOnInit(): void
  {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit(): void
  {
    if (this.form.valid) {
      this.authService.login(this.form.value.username, this.form.value.password).subscribe((isLoggedIn) =>
      {
        if (isLoggedIn) {
          //set 1s sleep time
          setTimeout(() =>
          {
            this.router.navigate(['/dashboard']);
          }, 500);

        }
        console.log('User is logged in:', isLoggedIn);
      }
      );



    } else {
      console.log('Form is invalid');
    }
  }


}
