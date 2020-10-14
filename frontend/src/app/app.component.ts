import { Router } from '@angular/router';
import { UserService } from './services/user/user.service';
import { Component } from '@angular/core';
import { User } from './models/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'careradius';
  currentUser: User;

  constructor(private userService: UserService, private router: Router) {
    // Call it observable because it can be changed from other page like login.
    this.userService.currentUser.subscribe(data => {
      this.currentUser = data;
    });
  }

  logOut() {
    this.userService.logout().subscribe(data => {
      this.router.navigate(['/login']);
    });
  }
}
