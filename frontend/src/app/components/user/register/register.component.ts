import { Router } from '@angular/router';
import { UserService } from './../../../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  errorMessage: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    if (this.userService.currentUserValue) {
      this.router.navigate(['/home']);
      return;
    }
  }

  register(){
    this.userService.register(this.user).subscribe(data => {
      this.router.navigate(['/login']);
    }, err => {
      if (!err || err.status !== 409) {
        this.errorMessage = 'Unexpected error occurred. Error : ' + err;
      }else {
        this.errorMessage = 'Username already exist';
      }
    });
  }

}
