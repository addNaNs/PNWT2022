import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Globals } from './globals'
import { SignInService } from './header/account/sign-in/sign-in.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  user = Globals.user;
  token = Globals.token
  loggedIn : boolean = false;

  constructor(private router : Router){}
  

  title = 'ELearning';

  logout(){
    Globals.cart = []
    localStorage.setItem("cart", JSON.stringify(Globals.cart));
    localStorage.removeItem('token')
    localStorage.removeItem('name')
    this.router.navigate(['/']).then(() => window.location.reload());
  }
  ngOnInit(): void {
    this.loggedIn = this.user.name.length != 0;
  }
}
