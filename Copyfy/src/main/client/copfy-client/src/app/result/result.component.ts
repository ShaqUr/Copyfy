import { Component, OnInit } from '@angular/core';
import { SearchService } from 'app/search.service';
import {AuthService} from 'app/auth.service';
import { User } from '../user';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  model: {
    songName: string
    playlistName: string,
    };

    model2: {
      songName: string
      username: string,
      };

  constructor(
    private authService: AuthService,
    private searchService: SearchService,
  ) {
    this.model = {
      "songName" : '',
      "playlistName" : '',
    }
    this.model2 = {
      "songName" : '',
      "username" : '',
    }
   }

  ngOnInit() {

  }

  onSubmit(){
    this.authService.addSongToPlaylist(this.model.songName, this.model.playlistName);
    console.log(this.model);
  }
  onShare(){
      this.authService.share(this.model2.username, this.model2.songName);
  }
}
