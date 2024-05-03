import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService
{

  constructor(private httpClient: HttpClient) { }

  generateRecommendation(history)
  {
    // This is where the recommendation generation logic will go
    return this.httpClient.post(environment.URL + "interaction/recommendation", history.id)

  }
}
