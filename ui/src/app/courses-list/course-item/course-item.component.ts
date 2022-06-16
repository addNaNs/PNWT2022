import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Globals } from 'src/app/globals';
import { CourseListModel } from 'src/app/models/course-list.model';
import { EnrollModel } from 'src/app/models/enroll.model';
import { EnrolledModel } from 'src/app/models/enrolled.model';
import { LessonModel } from 'src/app/models/lesson.model';
import { LessonsModel } from 'src/app/models/lessons.model';
import { QuizModel } from 'src/app/models/quiz.model';
import { UserModel } from 'src/app/models/user.model';
import { QuizService } from 'src/app/quiz/quiz.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
import { CoursesListService } from '../courses-list.service';

@Component({
  selector: 'app-course-item',
  templateUrl: './course-item.component.html',
  styleUrls: ['./course-item.component.scss'],
  providers: [CoursesListService]
})
export class CourseItemComponent implements OnInit {
  course : any;
  loggedIn : boolean = false;
  user : any;
  courseId1 : number = this.route.snapshot.params['courseId'];
  user_id : any;
  userList: UserModel[] = [];
  data : any;
  enrolledIn: boolean = false;
  lessonsForCourse : LessonModel[] = [];
  allInfoForCourse: any;
  quizes: QuizModel[] = [];

  constructor(private route: ActivatedRoute, private courseListService: CoursesListService,
    private userService: UserService, private quizService: QuizService) { }

  ngOnInit(): void {
    const courseId = this.route.snapshot.params['courseId'];
    console.log(courseId);
    
    this.loggedIn = Globals.user.name.length != 0;
    this.user = Globals.user;
    console.log(this.user)

    console.log(this.loggedIn)
    let jwt = localStorage.getItem("token");
    if(jwt!=null){
      let jwtData = jwt.split('.')[1]
      let decodedJwtJsonData = window.atob(jwtData)
      let decodedJwtData = JSON.parse(decodedJwtJsonData)
      this.user_id = decodedJwtData;
      console.log(decodedJwtData)
    }

    this.courseListService.getCourseByCourseId(courseId).subscribe(
      (response : CourseListModel) =>  {
        var course = {
          id: response.id,
          name: response.name,
          instructor: response.instructor,
          description: response.description,
        }
        console.log(response);
        this.course = course;

        (Object(response)).users.forEach( (el : any) => {
          console.log(el.id)
          console.log(this.user_id.user_id)
          if(el.id == this.user_id.user_id){
            this.enrolledIn = true;
          }
        })

        console.log(this.enrolledIn)
      }
    )
    this.userService.getUsers().subscribe(
      (response : UserModel[]) =>{
        for(var i=0; i<response.length; i++){
          var user = {
            id : response[i].id,
            name : response[i].name,
            password : response[i].password,
            username : response[i].username,
            email : response[i].email
          }
          this.userList = [...this.userList, user];
        }
        for(var i=0; i<this.userList.length; i++){
          if(this.userList[i].username==this.user_id.sub){
            this.user = this.userList[i];
            console.log(this.user);
            break;
          }
        }
      },
      (error : any)=>console.log(error)
    )
    console.log(this.courseId1);
    this.courseListService.getLessonsForCourse(this.courseId1).subscribe(
      (res : LessonsModel)=>{
        // for(var i=0; i<res.length; i++){
          var courseInfo = {
            id : res.id,
            name : res.name,
            description: res.description,
            instructor : res.instructor,
            users: res.users,
            lessons: res.lessons
          }
          // this.lessonsForCourse = [...this.lessonsForCourse, courseInfo.lessons];
          // console.log(this.lessonsForCourse);
          this.allInfoForCourse = courseInfo;
          console.log(this.allInfoForCourse);
        // }
        this.lessonsForCourse = this.allInfoForCourse.lessons;
        console.log(this.lessonsForCourse);
      }
    )
    this.quizService.getAllQuizesForCourse(this.courseId1).subscribe(
      (response: QuizModel[]) =>{
        for(var i=0; i<response.length; i++){
          var quiz = {
            id: response[i].id,
            title: response[i].title,
            nQuestions: response[i].nQuestions,
            questions: response[i].questions
          }
          console.log(quiz);
          this.quizes = [...this.quizes, quiz];
        }
      }
    )
  }

  addToMyCourse(){
    console.log(this.course.id)
    console.log(this.user.id)

    this.data = {
      "course_id" : Number(this.course.id),
      "user_id" : this.user.id
    }
    console.log(this.data);
    this.userService.postUserEnrollCourse(this.data).subscribe(
      (res : any) => {
        console.log(res);
      },
      (error : any)=>console.log(error)
    )
    

    if(this.loggedIn){
        Swal.fire(
          'Good job!',
          'Successfully enrolled this course!',
          'success'
        ).then(() => {
          this.enrolledIn = true;
        });
    }else{
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Please sign in!',
        footer: '<a href="/sigIn">Click on the header - login if you have accout. Otherwise, use register.</a>'
      })
    }
    
  }


}
