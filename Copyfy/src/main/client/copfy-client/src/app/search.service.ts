import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { User } from './user';
import { Song } from 'app/song';
@Injectable()
export class SearchService {
  users:User[];
  songs:Song[];
  constructor(
    private http: Http,
  ) {
    this.users=[];
    this.songs=[];
  }

  public searchUser(username: String): Promise<User[]>{
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
}
