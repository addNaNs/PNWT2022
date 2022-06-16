import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AlertPromise } from 'selenium-webdriver';
import { Globals } from 'src/app/globals';
import { SignInService } from './sign-in.service';
import { UserModel } from './userModel';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css'],
  providers: [SignInService]
})
export class SignInComponent implements OnInit {

  constructor(private signInService : SignInService, private router : Router) { }

  ngOnInit(): void {
  }

  redirectTo(uri:string){
    this.router.navigateByUrl('/brands', {skipLocationChange: true}).then(()=>
    this.router.navigate([uri]));
 }

  async onSubmit(event: any){
    this.signInService.login(event.username, event.password).subscribe(res =>{
      console.log(res.token);
      Globals.token = res.token;
      localStorage.setItem("token", res.token)
      localStorage.setItem("name", res.user.name)
      this.router.navigate(['/']).then(() => window.location.reload())
    });


    /*
    localStorage.setItem("token", '');
    console.log(localStorage.getItem("token"));
    console.log(event);
    console.log(Globals.user)

    this.signInService.getUsers().subscribe(
      (res : UserModel[]) => {
        let found : Boolean = false;
        for(var i=0; i<res.length; i++){
          if(res[i].username == event.username && event.password == "sifra"){
            console.log(res[i].username);
            found = true;
            Globals.user = {name : res[i].username}
            localStorage.setItem("token", Globals.user.name);
            while(localStorage.getItem("token")?.length == 0 || localStorage.getItem("token") == null){1 + 1 };
            this.redirectTo('/')
          }
        }
        
        if(!found){
          console.log("nema");
          alert("Bad username/assword combination")
        }

      },
      (err : any) => {console.log(err)}
    )
      */
    return;
  }
}
