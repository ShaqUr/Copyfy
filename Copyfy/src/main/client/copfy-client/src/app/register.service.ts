import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { User } from './user';

@Injectable()
export class RegisterService {


    user: User;
  
    constructor(
      private http: Http,
    ) { }
  
    public register(user: User): Promise<User> {
      const response$: Observable<any> = this.http.post('/api/user/register', user);
      const responsePromise: Promise<any> = response$.toPromise();
      return responsePromise
        .then(res => res.json())
        .then(loggedInUser => {
          this.user = loggedInUser;
          return loggedInUser;
        });
    }
  
  }