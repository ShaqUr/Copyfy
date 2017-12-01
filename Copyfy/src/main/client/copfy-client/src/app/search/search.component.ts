import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { Observable } from 'rxjs/observable';
import { SearchService } from 'app/search.service';
import { AuthService } from 'app/auth.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  
  @ViewChild('form') form;

  constructor(
    private searchService: SearchService,
    private authService: AuthService,
  ) {}

  ngOnInit() {
  }

}
