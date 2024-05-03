import { Component, OnInit } from '@angular/core';
import { History } from 'src/app/models/history';
import { Recommendation } from 'src/app/models/recommendation';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { RecommendationService } from 'src/app/services/recommendation/recommendation.service';

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

  constructor(private authService: AuthService, private recommendationService: RecommendationService)
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

    })
  }

}
