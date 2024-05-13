import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root'
})
export class StatisticService
{

  constructor(private httpClient: HttpClient, private authService: AuthService) { }

  getInteractionSize()
  {
    // This is where the recommendation generation logic will go
    return this.httpClient.post(environment.URL + "statistic/interaction/size", this.authService.user.id)

  }

  getRecommendationSize()
  {
    // This is where the recommendation generation logic will go
    return this.httpClient.post(environment.URL + "statistic/recommendation/size", this.authService.user.id)

  }

  getPurchaseSize()
  {
    // This is where the recommendation generation logic will go
    return this.httpClient.post(environment.URL + "statistic/purchase/size", this.authService.user.id)

  }

  getCoordinateSize()
  {
    // This is where the recommendation generation logic will go
    return this.httpClient.post(environment.URL + "statistic/coordinate/size", this.authService.user.id)

  }

}
