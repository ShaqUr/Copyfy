import { Component, OnInit, ViewChild } from '@angular/core';
import { SearchService } from 'app/search.service';
import { AuthService } from 'app/auth.service';
import { SongService } from 'app/song.service';
import { Observable } from 'rxjs/observable';
import { Router } from '@angular/router/src/router';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  model:{
    password: string,
    passwordAgain: string,
  }

  @ViewChild('form') form;
  constructor(
    private searchService: SearchService,
    private songService: SongService,
    private authService: AuthService,
    private router: Router,
  ) {
    if(this.authService.user.role=="ADMIN"){
      searchService.usersToAdminSettings();
      searchService.songsToAdminSettigns();
    }
    this.model={
      "password":'',
      "passwordAgain":'',
    };
   }
  ngOnInit() {
  }

  pwsNotMatching(): boolean{
    return(this.model.password!=this.model.passwordAgain);
   }
  onSubmit(){
     if(this.form.valid){
      if(this.pwsNotMatching()){}
      else{
        this.authService.changePassword(this.model.password)
        .then(() =>{
          this.router.navigateByUrl('');
          alert("Sikeres jelszó módosítás!")
        });
      }
    }
  }
}
