import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth-service';
import { Member } from '../../models/Member';
import { MemberService } from '../../services/member-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-page',
  imports: [],
  templateUrl: './profile-page.html',
  styleUrl: './profile-page.css'
})
export class ProfilePage implements OnInit{
  member!: Member;

  constructor(public authService: AuthService, public memberService: MemberService, private router: Router){}

  ngOnInit(): void {
    this.showUser();
  }

  showUser() {
    if(this.authService.getUserRole() === 'MEMBER') {
      this.memberService.getMember().subscribe({
        next: (data) => {this.member = data},
        error: (e) => {console.log('ERROR: ', e)}
      })
    }
  }

  removeMember() {
    if(this.authService.getUserRole() === 'MEMBER') {
      this.memberService.deleteMember().subscribe({
        next: (data) => {
          this.authService.logout();
          this.router.navigate(['/']);
        },
        error: (e) => {console.log('ERROR: ', e)}
      })
    }
  }
}
