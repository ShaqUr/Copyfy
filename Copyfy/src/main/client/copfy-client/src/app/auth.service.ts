import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { User } from './user';

@Injectable()
export class AuthService {
  user: User;
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
}