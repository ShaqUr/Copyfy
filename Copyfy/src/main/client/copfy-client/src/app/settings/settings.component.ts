import { Component, OnInit } from '@angular/core';
import { SearchService } from 'app/search.service';
import { AuthService } from 'app/auth.service';
import { SongService } from 'app/song.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  constructor(
    private searchService: SearchService,
    private songService: SongService,
    private authService: AuthService,
  ) {
    if(this.authService.user.role=="ADMIN"){
      searchService.usersToAdminSettings();
      searchService.songsToAdminSettigns();
    }
   }

  ngOnInit() {
  }

}
