import { AuthorizationComponent } from './components/authorization/authorization/authorization.component';
import { LoginComponent } from './components/user/login/login.component';
import { AuthorizationDetailsComponent } from './components/authorization/authorization-details/authorization-details.component';
import { ProfileComponent } from './components/user/profile/profile.component';
import { RegisterComponent } from './components/user/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [

  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'auth', component: AuthorizationComponent },
  { path: 'authorization-details/:internalId', component: AuthorizationDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
