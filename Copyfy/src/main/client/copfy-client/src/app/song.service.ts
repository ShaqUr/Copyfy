import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { Song } from './song';
import { AuthService } from 'app/auth.service';

@Injectable()
export class SongService {

  songs: Song[];

  constructor(
    private http: Http,
  ) { }
public getSongsByOwner(owner: String): Promise<Song[]>{
  const response$: Observable<any> = this.http.post('/api/songs/owner', owner);
  const responsePromise: Promise<any> = response$.toPromise();
  return responsePromise
    .then(res => res.json())
    .then(responseSongs => {
      this.songs = responseSongs;
      console.log(responseSongs);
      return responseSongs;
    });
}


}
