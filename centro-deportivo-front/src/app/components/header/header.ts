import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [RouterLink],
  templateUrl: './header.html',
  styleUrl: './header.css'
})
export class Header {
  
  constructor(private router: Router){}
  // constructor(public authService: AuthService, private router: Router){}

  logout(){
    // this.authService.logout();
    this.router.navigate(['/']);
  }
}
