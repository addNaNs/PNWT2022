import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Globals } from '../globals';
import { CourseListModel } from '../models/course-list.model';
import { PostDataModel } from '../models/post-data.model';
import { CoursesListService } from './courses-list.service';

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css'],
  providers: [CoursesListService]
})
export class CoursesListComponent implements OnInit {
  course : any;
  signedIn: boolean = false;
  user = Globals.user;
  postingCourse: boolean = false;

  coursesList: CourseListModel[] = [];

  constructor(private coursesListService: CoursesListService, private route: ActivatedRoute) { }
  

  ngOnInit(): void {
    const courseId = this.route.snapshot.params['id'];

    if(this.user.name.length != 0){
      this.signedIn = true;
    }

    this.coursesListService.getCoursesFromDB().subscribe(
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
  postingNewCourse(){
    this.postingCourse = true;
  }
  onClickAddNewCourseForm(data: any){
    console.log(data);
    var postData = {
      "name": data.courseName,
      "description": data.description,
      // "instructorId": data.instructorId
    }
    console.log(postData);
    this.coursesListService.postNewCourse(postData);
  }

}
