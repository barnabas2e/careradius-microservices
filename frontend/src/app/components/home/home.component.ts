import { Member } from './../../models/member';
import { Authorization } from './../../models/auth/authorization';
import { AuthorizationService } from './../../services/authorization/authorization.service';
import { MemberService } from './../../services/member/member.service';
import { UserService } from './../../services/user/user.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Transaction } from 'src/app/models/auth/transaction';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  authorizationList: Array<Authorization>;
  member: Member = new Member();
  currentUser: User;
  memberId: string;
  infoMessage: string;
  errorMessage: string;

  constructor(private router: Router,
              private userService: UserService,
              private memberService: MemberService,
              private authorizationService: AuthorizationService) {

      this.currentUser = userService.currentUserValue;
      }

  ngOnInit(): void {
    if (!this.currentUser) {
      this.router.navigate(['/login']);
    }
    this.findAllAuthorizations();
  }

  findAllAuthorizations() {
    return this.authorizationService.findAllAuthorizations().subscribe(data => {
      this.authorizationList = data;
    });
  }

addAuth() {
  this.router.navigate(['/auth']);
}
/**
 * View Auth details
 * @param authorization
 */
 authDetails(authorization: Authorization) {
    localStorage.setItem('currentAuth', JSON.stringify(authorization));
    this.router.navigate(['/authorization-details', authorization.internalId]);
  }
}
