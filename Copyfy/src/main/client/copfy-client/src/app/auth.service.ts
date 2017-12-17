import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { User } from './user';
import { Song } from './song';

@Injectable()
export class AuthService {
  user: User;
  privatesongs:Song[];
  private banned: boolean;
  constructor(
    private http: Http,
  ) {
    this.banned=false;
  }
  public isLoggedIn(): boolean{
    return (this.user!==undefined);
  }
  public isBanned(): boolean{
    return this.banned;
  }
  public login(user: User): Promise<User> {
    const response$: Observable<any> = this.http.post('/api/user/login', user);
    const responsePromise: Promise<any> = response$.toPromise();
    return responsePromise
      .then(res => res.json())
      .then(loggedInUser => {
        this.user = loggedInUser;
        if(this.user.role==="BANNED"){
          this.banned=true;
          this.user=undefined;
          return null;
        }else{
          this.banned=false;
          return loggedInUser;
        }
      });
  }
  public bann(username: string){
    if(this.user.role=="ADMIN"){
      const response$: Observable<any> = this.http.post('/api/user/bann', username);
      const responsePromise: Promise<any> = response$.toPromise();
      return responsePromise
        .then(msg =>{
          alert(msg.text());
        });
    }else{
      alert("Felkerültél a bannlistára muhahahaha!");
    }
  }
  public changePassword(newPassword: string){
    this.user.password=newPassword;
    const response$: Observable<any> = this.http.post('/api/user/changepassword', this.user);
    const responsePromise: Promise<any> = response$.toPromise();
    return responsePromise
    .then(res => res.json())
    .then(loggedInUser => {
      this.user = loggedInUser;
      this.banned=false;
      return loggedInUser;
      });
  }
  public addNewPlaylist(name: string): Promise<User>{
      const response$: Observable<any> = this.http.post('/api/user/addplaylist', name);
      const responsePromise: Promise<any> = response$.toPromise();
      return responsePromise
      .then(res => res.json())
      .then(loggedInUser => {
        this.user = loggedInUser;
        return loggedInUser;
    });
  }

  public addSongToPlaylist(sn: string, pn: string): Promise<User>{
    let model={
      songName: sn,
      playlistName: pn,
    };
    const response$: Observable<any> = this.http.post('/api/user/addtoplaylist', model);
      const responsePromise: Promise<any> = response$.toPromise();
      return responsePromise
      .then(res => res.json())
      .then(loggedInUser => {
        this.user = loggedInUser;
        return loggedInUser;
    });
  }

  public privateSongs(user:User):Promise<Song[]>{
    const response$: Observable<any> = this.http.post('/api/songs/private', this.user);
    const responsePromise: Promise<any> = response$.toPromise();
    return responsePromise
      .then(res => res.json())
      .then(resultSongs =>{
        this.privatesongs=resultSongs;
        return resultSongs;
      });
  }

  public share(us:string, sn:string){
    console.log("a felhasznalo neve: " + us);
    let model={
      userName: us,
      songName: sn,
    };
    console.log(model.userName);
    const response$: Observable<any> = this.http.post('/api/songs/share', model);
    const responsePromise: Promise<any> = response$.toPromise();
  }
}
