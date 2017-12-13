import { Component, OnInit } from '@angular/core';
import { AuthService } from 'app/auth.service';
import {SearchService} from 'app/search.service';
import { ViewChild } from '@angular/core/src/metadata/di';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  model:{
    searchedExpression : string;
    searchedType : string;
  }
  @ViewChild('form') from;

  constructor(
    private searchService : SearchService,
    private authService : AuthService,
  ) {
    this.model={
      "searchedExpression":'',
      "searchedType":'song',
    }
  }

  ngOnInit() {
  }
  onSubmit(){
    if(this.model.searchedType==='user'){
      console.log("USER");
    }else{
      console.log(this.model);
    }
  }
  public isLoggedIn(): boolean{
    return this.authService.isLoggedIn();
  }

}
