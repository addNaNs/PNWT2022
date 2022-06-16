import { Component, OnInit } from '@angular/core';
import { CoursesListService } from '../courses-list/courses-list.service';
import { CourseListModel } from '../models/course-list.model';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent implements OnInit {
  coursesList : CourseListModel[] = [];
  constructor(private courseService: CoursesListService) { }

  ngOnInit(): void {
    this.courseService.getCoursesFromDB().subscribe(
      (response : CourseListModel[]) => {
        this.coursesList = [];
        console.log(response);
        for(var i=0; i< response.length; i++){
          console.log(response[i]);
          var course = {
            id: response[i].id,
            name: response[i].name,
            description: response[i].description,
            instructor: response[i].instructor,
        }
          console.log(course);
          this.coursesList = [...this.coursesList, course]
        }
      },
      (error : any)=>console.log(error)
    )
  }

}
