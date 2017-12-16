import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { AuthService } from 'app/auth.service';
import { Playlist } from 'app/playlist';
import { PlayerService } from 'app/player.service';
import { Song } from 'app/song';

@Component({
  selector: 'app-playlists',
  templateUrl: './playlists.component.html',
  styleUrls: ['./playlists.component.css']
})
export class PlaylistsComponent implements OnInit {
  model: User;
  model2: {playlistname: string};
  playlists: Playlist[];
  @ViewChild('form') form
  constructor(
    private authService: AuthService,
    private playerService: PlayerService,
    private router: Router,
  ) {
    this.model = authService.user;
    this.model2 = {playlistname : ""};
    this.playlists=this.model.playlists;
  }

  ngOnInit() {
  }
  public getPlaylistName(playlist: Playlist):string{
    return playlist.name;
  }
  public addPlaylist(){}

  playPLaylist(songs: Song[]){
    this.playerService.addSongs(songs);
  }

  onSubmit(){
      this.authService.addNewPlaylist(this.model2.playlistname);
  }
}
