import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth.service';
import { User } from 'src/app/models/user';
import { History } from 'src/app/models/history';
import { Interaction } from 'src/app/models/interaction';

@Injectable({
  providedIn: 'root'
})
export class UploadService
{

  private URL = environment.URL;

  constructor(private http: HttpClient, private authService: AuthService) { }

  uploadHistory(interactions: Interaction[]): void
  {
    let newHistory: History = new History();
    newHistory.interactions = [...interactions];

    let user: User = this.authService.user

    this.http.post(this.URL + 'history/upload', { user: user, history: newHistory }).subscribe(
      (response) =>
      {
        console.log(response);
        alert('History uploaded successfully');
      },
      (error) =>
      {
        console.error(error);
        alert('Error uploading history');
      }
    );
  }
}
