import { Component, input } from '@angular/core';
import { Member } from '../../models/Member';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-member-profile-card',
  imports: [RouterLink],
  templateUrl: './member-profile-card.html',
  styleUrl: './member-profile-card.css'
})
export class MemberProfileCard {
  member = input.required<Member>();
}
