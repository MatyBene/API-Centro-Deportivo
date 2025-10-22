import { Component, OnInit } from '@angular/core';
import { TokenPayLoad } from '../../models/Auth';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-profile-page',
  imports: [],
  templateUrl: './profile-page.html',
  styleUrl: './profile-page.css'
})
export class ProfilePage implements OnInit{
  decodedToken!: TokenPayLoad | null;

  constructor(public authService: AuthService){}

  ngOnInit(): void {
      this.decodedToken = this.authService.getDecodedToken();
  }
}
