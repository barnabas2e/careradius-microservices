import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { AuthorizationService } from './../../../services/authorization/authorization.service';
import { UserService } from './../../../services/user/user.service';
import { MemberService } from './../../../services/member/member.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Transaction } from 'src/app/models/auth/transaction';
import { Member } from 'src/app/models/member';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

   currentUser: User;
   transactionList: Array<Transaction>;
   member: Member = new Member();
   errorMessage: string;

  constructor(private userService: UserService,
              private memberService: MemberService,
              private authorizationService: AuthorizationService,
              private router: Router) {

      this.currentUser = this.userService.currentUserValue;
      if (this.currentUser) {
       // this.transactionList = JSON.parse(localStorage.getItem('current-transactionOfMember'));
      }
     }

  ngOnInit(): void {
    if (!this.currentUser) {
      this.router.navigate(['/login']);
      return;
    }
  }

  findMember(member: Member) {

    this.memberService.findMemberbyId(member.memberId).subscribe(data => {
    this.member = data;
    const memberUid = this.member?.internalId;

    return this.findTransactionOfMember(memberUid);
    },
    err => {
      this.errorMessage = 'Unable to retrieve member';
    });
  }

  findTransactionOfMember(memberInternalId: number){
    this.authorizationService.findTransactionOfMember(memberInternalId).subscribe(data => {
      this.transactionList = data;
      // store locally
      localStorage.setItem('current-transactionOfMember', JSON.stringify(this.transactionList));
    },
    err => {
      this.errorMessage = 'Unable to retrieve Authorization for member';
    }
    );
  }
}
