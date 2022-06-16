import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseListModel } from '../models/course-list.model';
import { EnrolledModel } from '../models/enrolled.model';
import { UserModel } from '../models/user.model';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-my-courses',
  templateUrl: './my-courses.component.html',
  styleUrls: ['./my-courses.component.css']
})
export class MyCoursesComponent implements OnInit {
  users : EnrolledModel[] = [];
  myCourses: CourseListModel[] = [];
  user : EnrolledModel = {
    id: 0,
    name: "",
    email: "",
    courses: [],
    password: ""
  };
  courseId = this.route.snapshot.params['courseId'];


  constructor(private userService : UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.userService.getAllUsers().subscribe(
      (response : EnrolledModel[]) => {
        for(var i=0; i<response.length; i++){
          var user = {
            id: response[i].id,
            name: response[i].name,
            password: response[i].password,
            email: response[i].email,
            courses: response[i].courses
          }
          console.log(user);
          this.users = [...this.users, user];
        }
        localStorage.getItem("name")
        for(var i=0; i<this.users.length; i++){
          if(this.users[i].name==localStorage.getItem("name")){
            this.user = this.users[i];
            console.log(this.user);
            break;
          }
        }
        console.log(this.user.courses);
        this.myCourses = this.user.courses;
        console.log(this.myCourses);
      },
      (error : any)=>console.log(error)
    )
   
  }

}
