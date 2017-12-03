import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { AuthService } from 'app/auth.service';
import { Playlist } from 'app/playlist';

@Component({
  selector: 'app-playlists',
  templateUrl: './playlists.component.html',
  styleUrls: ['./playlists.component.css']
})
export class PlaylistsComponent implements OnInit {
  model: User;
  playlists: Playlist[];
  constructor(
    private authService: AuthService,
    private router: Router,
  ) {
    this.model = authService.user;
    this.playlists=this.model.playlists;
  }

  ngOnInit() {
  }
  public getPlaylistName(playlist: Playlist):string{
    console.log(playlist.name);
    return playlist.name;
  }
}
