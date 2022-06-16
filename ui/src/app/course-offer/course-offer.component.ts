import { Component, OnInit } from '@angular/core';
import { CourseOfferHomeService } from './course-offer.service';

@Component({
  selector: 'app-course-offer',
  templateUrl: './course-offer.component.html',
  styleUrls: ['./course-offer.component.css'],
  providers: [CourseOfferHomeService]
})
export class CourseOfferComponent implements OnInit {

  courses: {itemId: string; name: string, description: string, imagePath: string}[] = [];

  constructor(private courseOfferHomeService : CourseOfferHomeService) { }

  ngOnInit(): void {
    this.courses = this.courseOfferHomeService.getItems();
  } 

}
