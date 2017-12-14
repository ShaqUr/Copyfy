import { Component, OnInit } from '@angular/core';
import { SearchService } from 'app/search.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  constructor(
    private searchService: SearchService,
  ) { }

  ngOnInit() {
  }

}
