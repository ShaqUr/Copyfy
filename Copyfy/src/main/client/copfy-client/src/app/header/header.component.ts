import { Component, OnInit } from '@angular/core';
import { AuthService } from 'app/auth.service';
import {SearchService} from 'app/search.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private searchService : SearchService,
    private authService : AuthService,
  ) { }

  ngOnInit() {
  }
  onSubmit(){
    
  }
  public isLoggedIn(): boolean{
    return this.authService.isLoggedIn();
  }

}
