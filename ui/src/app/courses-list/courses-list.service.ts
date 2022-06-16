import { Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Observable } from "rxjs";
import { CourseListModel } from "../models/course-list.model";
import Swal from "sweetalert2";
import { LessonsModel } from "../models/lessons.model";
import { LessonModel } from "../models/lesson.model";
import { PostDataModel } from "../models/post-data.model";
import { Globals } from "../globals";
import { PostLessonModel } from "../models/post-lesson.model";


@Injectable({
    providedIn:'root'
})


export class CoursesListService{
    constructor(private http : HttpClient){
    }

    public getCoursesFromDB():Observable<CourseListModel[]>{
           return this.http.get<CourseListModel[]>('http://localhost:8070/course-app/course'); 
    }
    public getCourseByCourseId(id: number):Observable<any>{
        return this.http.get<any>('http://localhost:8070/course-app/course/' + id); 
    }
    public getLessonsForCourse(id: number):Observable<LessonsModel>{
        return this.http.get<LessonsModel>('http://localhost:8070/course-app/course/' + id); 
    }
    public getLessonsByLessonId(id: number):Observable<LessonModel>{
        return this.http.get<LessonModel>('http://localhost:8070/course-app/lesson/' + id); 
    }
    public postNewCourse(data : PostDataModel): Observable<PostDataModel>{
        const headers = { 
            'content-type': 'application/json',
            'Authorization': "Bearer " + Globals.user.token
        }  
        const body=JSON.stringify(data);
        console.log(body);

        var tmp = this.http.post<PostDataModel>('http://localhost:8070/course-app/course', body, {'headers': headers}); 
        console.log(tmp);
        tmp.subscribe(
          res => {
              Swal.fire(
                  'Good job!',
                  'You have successfully added new course!',
                  'success'
              );
              console.log(res)
          },
          err => {console.log(err); 
            Swal.fire(
            'Something went wrong!',
            'Please check have you enter required data!',
            'error'
        );}
        );
        return tmp;
    }
    public postNewLesson(data : PostLessonModel): Observable<PostLessonModel>{
        const headers = { 
            'content-type': 'application/json',
            'Authorization': "Bearer " + Globals.user.token
        }  
        const body=JSON.stringify(data);
        console.log(body);

        var tmp = this.http.post<PostLessonModel>('http://localhost:8070/course-app/lesson', body, {'headers': headers}); 
        console.log(tmp);
        tmp.subscribe(
          res => {
              Swal.fire(
                  'Good job!',
                  'You have successfully added new lesson!',
                  'success'
              );
              console.log(res)
          },
          err => {console.log(err); 
            Swal.fire(
            'Something went wrong!',
            'Please check have you enter required data!',
            'error'
        );}
        );
        return tmp;
    }
}