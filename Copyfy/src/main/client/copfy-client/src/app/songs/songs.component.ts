import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Song } from '../song';
import { RegisterService } from '../register.service';
import { Observable } from 'rxjs/observable';
import { AuthService } from 'app/auth.service';
import { SongService } from 'app/song.service';

@Component({
  selector: 'app-songs',
  templateUrl: './songs.component.html',
  styleUrls: ['./songs.component.css']
})
export class SongsComponent implements OnInit {
  model: Song[];
  constructor(
    private songService : SongService,
    private authService : AuthService,
  ) {}

  ngOnInit() {
  }
  ngAfterViewInit(){
    this.loadSongs();
  }
  public loadSongs(){
    this.songService.getSongsByOwner(this.authService.user.username);
  }
}
