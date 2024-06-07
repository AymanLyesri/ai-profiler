import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { History } from 'src/app/models/history';
import { Recommendation } from 'src/app/models/recommendation';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { RecommendationService } from 'src/app/services/recommendation/recommendation.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.scss']
})
export class TablesComponent implements OnInit
{

  currentHistory: History;
  currentObjects: Object[];
  user: User;

  constructor(private authService: AuthService,
    private recommendationService: RecommendationService,
    private http: HttpClient)
  {
    this.user = this.authService.user;
  }

  ngOnInit()
  {
  }

  getKeys(obj: Object): string[]
  {
    return Object.keys(obj);
  }

  generateRecommendation(history)
  {
    // This is where the recommendation generation logic will go
    this.recommendationService.generateRecommendation(history).subscribe((response: any) =>
    {
      console.log(response);
      this.currentHistory.recommendation = response.data;
      alert('Recommendation generated successfully');

    })
  }

  public deleteHistory(historyId: number)
  {
    this.http.post(environment.URL + `history/delete`, historyId).subscribe((response: any) =>
    {
      console.log(response);
      alert('History deleted successfully');

      this.user.histories = this.user.histories.filter(history => history.id !== historyId);
      this.currentHistory = null;
    })
  }

}
