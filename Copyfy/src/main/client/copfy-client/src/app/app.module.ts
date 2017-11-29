import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from 'app/login.service';
import { IndexComponent } from './index/index.component';
import { RegistrationComponent } from './registration/registration.component';

const appRoutes : Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'index', component: IndexComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: '**', redirectTo: '/index'},
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    IndexComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(
      appRoutes, {enableTracing: true}
    )
  ],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
