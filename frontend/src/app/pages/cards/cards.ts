import { Component, OnInit } from '@angular/core';


import { StatisticService } from 'src/app/services/recommendation copy/statistic.service';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.scss']
})
export class CardsComponent implements OnInit
{

  public interactions: number;
  public recommendations: number;
  public purchases: number;
  public coordinates: number;

  constructor(private statisticService: StatisticService) { }

  ngOnInit()
  {
    this.statisticService.getInteractionSize().subscribe((data: any) =>
    {
      this.interactions = data.data;
    }
    );
    this.statisticService.getRecommendationSize().subscribe((data: any) =>
    {
      this.recommendations = data.data;
    }
    );
    this.statisticService.getPurchaseSize().subscribe((data: any) =>
    {
      this.purchases = data.data;
    }
    );
    this.statisticService.getCoordinateSize().subscribe((data: any) =>
    {
      this.coordinates = data.data;
    }
    );

  }



}
