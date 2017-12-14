import { Component, OnInit } from '@angular/core';
import {Song} from '../song';
import {AuthService} from '../auth.service';
import { PlayerService } from 'app/player.service';
import { Subscription } from 'rxjs/Subscription';
@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {
  song: Song;
  sub: Subscription;
  constructor(
    private authService : AuthService,
    private playerService : PlayerService,
  ) {
    this.song={
      owner: "",
      base64str: "",
      tags: [],
      title:"",
      access:"",
    };
    this.sub=this.playerService.addSong$.subscribe(
      () => {
        this.song=this.getSong();
        console.log(this.song);
      }
    );
  }

  ngOnInit() {
  }
  ngOnDestroy(){
    this.sub.unsubscribe();
  }
  public audioEnded(){
    if(this.playerService.songs.length>0){
      this.song=this.getSong();
    }
  }
  public isLoggedIn(): boolean{
    return this.authService.isLoggedIn();
  }
  public getSong(): Song{
    if(this.playerService.songs.length>0){
      return this.playerService.getSong();
    }else{
      return {
        owner: "",
        base64str: "",
        tags: [],
        title:"",
        access:"",
      };
    }
  }
}
