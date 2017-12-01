import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { RegisterService } from '../register.service';
import { Observable } from 'rxjs/observable';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  model: User;
  validationMessage: string;

  @ViewChild('form') form;

  constructor(
    private registerService: RegisterService,
    private router: Router,
  ) {
    this.model = {
      "username": '',
      "password": '',
      "email": '',
    };
  }

  ngOnInit() {
  }
  onSubmit() {
    if (this.form.valid) {
      this.registerService.register(this.model)
        .then(() =>{
          this.router.navigateByUrl('/index');
        })
        .catch(()=> {
          this.validationMessage='A felhasználónév, vagy az e-mail cím már használatban van.';
        })
    }
  }
}
