import { Component, OnInit } from '@angular/core';
import { History } from 'src/app/models/history';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.scss']
})
export class TablesComponent implements OnInit
{

  currentHistory: History;
  user: User;

  constructor(private authService: AuthService)
  {
    this.user = this.authService.user;
  }

  ngOnInit()
  {
  }

}
