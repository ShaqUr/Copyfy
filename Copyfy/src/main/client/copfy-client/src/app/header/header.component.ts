import { Component, OnInit } from '@angular/core';
import { AuthService } from 'app/auth.service';
import {SearchService} from 'app/search.service';
import { ViewChild } from '@angular/core/src/metadata/di';
import { Router } from '@angular/router/src/router';

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
    private router: Router,
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
      console.log("User kereső on duty!");
      this.searchService.searchUser(this.model.searchedExpression)
        .then(() =>{
          this.router.navigateByUrl('/results')
        });
        this.authService.privateSongs(this.authService.user)
        .then(() =>{
          this.router.navigateByUrl('/results')
        });
    }else{
      this.searchService.searchSongs(this.model.searchedExpression)
        .then(() =>{
          this.router.navigateByUrl('/results')
        });
      console.log(this.model);
    }
  }
  public isLoggedIn(): boolean{
    return this.authService.isLoggedIn();
  }

}
