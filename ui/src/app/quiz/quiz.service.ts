import { Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';

import { Observable } from "rxjs";
import { CourseListModel } from "../models/course-list.model";
import Swal from "sweetalert2";
import { QuizModel } from "../models/quiz.model";
import { QuestionQuizModel } from "../models/question-quiz.model";
import { ReturningQuizModel } from "../models/returning-quiz.model";
import { Globals } from "../globals";



@Injectable({
    providedIn:'root'
})


export class QuizService{
    constructor(private http : HttpClient){
    }

    public getAllQuizesForCourse(courseId: number):Observable<QuizModel[]>{
           return this.http.get<QuizModel[]>('http://localhost:8070/quiz-app/quiz/course/' + courseId); 
    }

    public getAllQuestionsForQuiz(quizId: number):Observable<ReturningQuizModel>{
        var tmp = this.http.get<ReturningQuizModel>('http://localhost:8070/quiz-app/quiz/attempt/' + quizId); 
        console.log(tmp);
        return tmp;
    }

    public postQuizResults(quizId: number, score:number):Observable<ReturningQuizModel>{
        const headers = { 
            'content-type': 'application/json',
            'Authorization': "Bearer " + Globals.user.token
        }
        const body=JSON.stringify({
            'points' : score,
            'quiz_id' : quizId
        });

        var tmp = this.http.post<any>(
            'http://localhost:8070/quiz-app/quiz/attempt',
             body, 
             {'headers' : headers}
        ); 
        console.log(tmp);
        return tmp;
    }
}