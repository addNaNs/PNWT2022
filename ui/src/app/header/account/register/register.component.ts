import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Globals } from 'src/app/globals';
import { RegisterService } from './register.service';
import { Router } from '@angular/router';
//import * as EventEmitter from 'node:events';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'], 
  providers: [RegisterService]
})
export class RegisterComponent implements OnInit {

  constructor(private registerService : RegisterService, private router : Router) { }

  ngOnInit(): void {
  }

  async onSubmit(event: any){
    console.log(event)
    if(event.password != event.verifypassword){
      alert("Passwords don't match")
      return
    }

    this.registerService.register(event.name, event.username, event.email, event.password).subscribe(res =>{
      this.router.navigate(['/'])
    });
  }


}
