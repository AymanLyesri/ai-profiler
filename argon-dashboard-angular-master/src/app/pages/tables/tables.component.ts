import { Component, OnInit } from '@angular/core';
import { Interaction } from 'src/app/models/interaction';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.scss']
})
export class TablesComponent implements OnInit
{

  interactions: Interaction[];

  constructor() { }

  ngOnInit()
  {
  }

}
