import { Member } from './../../../models/member';
import { AuthorizationService } from './../../../services/authorization/authorization.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
import { Authorization } from 'src/app/models/auth/authorization';

@Component({
  selector: 'app-authorization-details',
  templateUrl: './authorization-details.component.html',
  styleUrls: ['./authorization-details.component.css']
})
export class AuthorizationDetailsComponent implements OnInit {

  authInternalId: number;
  currentAuth: Authorization;
  memberList: Array<string>; // using string for member type since we are getting via memberClient in the backend


  constructor(private route: ActivatedRoute, private authService: AuthorizationService) {
    this.currentAuth = JSON.parse(localStorage.getItem('currentAuth'));
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      // route when there is internalId in the parameters
      if (params.has('internalId')) {
        // tslint:disable-next-line: radix
        this.authInternalId = Number.parseInt(params.get('internalId'));
        this.findMemberAuthorizations();
      }
    });
  }

  findMemberAuthorizations() {
    this.authService.findMemberAuthorization(this.authInternalId).subscribe(response => {
      this.memberList = response;
    });
  }
}
