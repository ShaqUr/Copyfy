import { Component, OnInit } from '@angular/core';
import { SearchService } from 'app/search.service';
import {AuthService} from 'app/auth.service';
@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  model: {playlistname: string, songtitle: string};
  constructor(
    private authService: AuthService,
    private searchService: SearchService,
  ) {
    this.model = {playlistname : "", songtitle : ""}
   }

  ngOnInit() {

  }

  onsubmit(){
    this.authService.addSongToPlaylist(this.model);
  }
}
