import { Router } from '@angular/router';
import { ContactMethod } from './../../../models/auth/contactMethod';
import { MemberService } from './../../../services/member/member.service';
import { AuthorizationService } from './../../../services/authorization/authorization.service';
import { UserService } from './../../../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { Authorization } from 'src/app/models/auth/authorization';
import { User } from 'src/app/models/user';
import { Member } from 'src/app/models/member';
import { Transaction } from 'src/app/models/auth/transaction';
import { FormControl, Validators } from '@angular/forms';
import { $ } from 'protractor';

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  member: Member;
  memberId: string;
  currentUser: User;
  authorization: Authorization = new Authorization();
  contactMethod = ContactMethod;
  errorMessage: string;
  infoMessage: string;
  memberUid: number;
  contactMethods = [ContactMethod.EMAIL, ContactMethod.FAX, ContactMethod.PHONE, ContactMethod.VIDEO];
  isValidDateRange = false;

  constructor(private userService: UserService,
              private authorizationService: AuthorizationService,
              private memberService: MemberService,
              private router: Router) {
    this.currentUser = userService.currentUserValue;
  }

  ngOnInit(): void {
    if (!this.currentUser) {
      this.router.navigate(['/login']);
    }
  }

  getMemberUid() {
    // get the member internalId using memberId
   this.memberService.findMemberbyId(this.memberId).subscribe(data => {
      this.member = data;
      this.memberUid = this.member.internalId;
    }, err => {
      this.errorMessage = 'Member not found';
    });
  }

  initiateAuth(authorization: Authorization) {
    if (!this.currentUser) {
      this.errorMessage = 'User must sign-in to iniatite authorization for a member';
      return;
    }
    // check for date valid date range
    this.isValidDateRange = this.validateDates(authorization.dateValidFrom, authorization.dateValidTo);

    console.log('Valid From Date:', authorization.dateValidFrom + 'and Valid To Date:', authorization.dateValidTo);
    const promise = new Promise((resolve, reject) => {
    if (this.isValidDateRange) {
      setTimeout(() => {
        const transaction = new Transaction();

        transaction.memberInternalId = this.memberUid;
        transaction.authorization = authorization;
        transaction.createdBy = this.currentUser.id;

        this.authorizationService.initiate(transaction).subscribe(data => {
            this.infoMessage = 'Successfully Initiated Authorization';
          }, err => {
            this.errorMessage = 'Unexpected error occurred while trying to initiate Authorization';
          });

        resolve();

      }, 1500);
    }
    }); // end of promise instantiation

    this.getMemberUid();

    return promise;
  }

  validateDates(date1: Date, date2: Date) {
    this.isValidDateRange = true;
    if ((date1 && date2) && (date2 < date1)) {
      this.errorMessage = '"Valid To" date should not be greater than "Valid From" date';
      this.isValidDateRange = false;
    } else if (!date1 && date2) {
      this.errorMessage = 'Invalid date range between "Valid To" and "Valid From" dates';
      this.isValidDateRange = false;
    }
    return this.isValidDateRange;
  }

}
