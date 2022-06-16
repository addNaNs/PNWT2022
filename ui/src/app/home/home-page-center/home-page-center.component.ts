import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home-page-center',
  templateUrl: './home-page-center.component.html',
  styleUrls: ['./home-page-center.component.css'],
  // providers: [FormsModule]
})
export class HomePageCenterComponent implements OnInit {

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(event: any){
    //let inputValue = event.form?.value;
    console.log(event);
    console.log(event.query);
    this.router.navigate(['/search/'+event.search]);
    return;
  }

}
