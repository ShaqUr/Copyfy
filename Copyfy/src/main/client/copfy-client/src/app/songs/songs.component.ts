import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Song } from '../song';
import { RegisterService } from '../register.service';
import { Observable } from 'rxjs/observable';
import { AuthService } from 'app/auth.service';
import { SongService } from 'app/song.service';
import { PlayerService } from 'app/player.service';

@Component({
  selector: 'app-songs',
  templateUrl: './songs.component.html',
  styleUrls: ['./songs.component.css']
})
export class SongsComponent implements OnInit {
  model: Song[];
  songsloaded:boolean;
  constructor(
    private songService : SongService,
    private authService : AuthService,
    private playerService: PlayerService,
  ) {
    this.songsloaded= false;
  }

  ngOnInit() {
  }
  public loadSongs(){
    if(!this.songsloaded){
      this.songService.getSongsByOwner(this.authService.user.username);
      this.songsloaded=true;
    }
  }
  public addSong(song: Song){
    this.playerService.addSong(song);
  }
}
