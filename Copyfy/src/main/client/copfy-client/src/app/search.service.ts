import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { User } from './user';
import { Song } from 'app/song';
@Injectable()
export class SearchService {
  users:String[];
  songs:Song[];
  constructor(
    private http: Http,
  ) {
    this.users=[];
    this.songs=[];
  }

  public usersToAdminSettings(){
    this.searchUser("");
  }
  public songsToAdminSettigns(): Promise<Song[]>{
    const response$: Observable<any> = this.http.post('/api/songs/searchall', "");
    const responsePromise: Promise<any> = response$.toPromise();
    return responsePromise
      .then(res => res.json())
      .then(resultSongs =>{
        this.songs=resultSongs;
        return resultSongs;
      });
  }
  public searchUser(username: String): Promise<String[]>{
    if(username===""){
      const response$: Observable<any> = this.http.post('/api/user/searchall', "");
      const responsePromise: Promise<any> = response$.toPromise();
      return responsePromise
        .then(res => res.json())
        .then(resultUsers =>{
          this.users=resultUsers;
          return resultUsers;
        });
    }else{
      const response$: Observable<any> = this.http.post('/api/user/search', username);
      const responsePromise: Promise<any> = response$.toPromise();
      return responsePromise
        .then(res => res.json())
        .then(resultUsers =>{
          this.users=resultUsers;
          return resultUsers;
        });
    }

  }

  public searchSongs(tag:string): Promise<Song[]>{
      const response$: Observable<any> = this.http.post('/api/songs/search', tag);
      const responsePromise: Promise<any> = response$.toPromise();
      return responsePromise
        .then(res => res.json())
        .then(resultSongs =>{
          this.songs=resultSongs;
          return resultSongs;
        });
  }

}
