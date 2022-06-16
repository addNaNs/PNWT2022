import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { QuestionQuizModel } from '../models/question-quiz.model';
import { ReturningQuizModel } from '../models/returning-quiz.model';
import { QuizService } from './quiz.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  questionsArray: any;
  questionsAll: QuestionQuizModel[] = [];
  addQuizForm: any;
  quizId : number = -1;

  constructor(private quizService: QuizService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.quizId = this.route.snapshot.params["quizId"];
    console.log(this.quizId);

    this.quizService.getAllQuestionsForQuiz(this.quizId).subscribe(
      (response: ReturningQuizModel) =>{
        console.log(response);
          var questionQuiz = {
            questions: response.questions
          }
          this.questionsArray = questionQuiz;
          console.log(questionQuiz);
          console.log(this.questionsArray.questions.length);

          for(var i=0; i<this.questionsArray.questions.length; i++){
            var questionQuiz1 = {
              id: this.questionsArray.questions[i].id,
              text: this.questionsArray.questions[i].text,
              correctAnswer: this.questionsArray.questions[i].correctAnswer,
              wrongAnswer1: this.questionsArray.questions[i].wrongAnswer1,
              wrongAnswer2: this.questionsArray.questions[i].wrongAnswer2,
              wrongAnswer3: this.questionsArray.questions[i].wrongAnswer3
            }
            console.log(questionQuiz1);
            this.questionsAll = [...this.questionsAll, questionQuiz1];
          }
      },
      (error : any)=>console.log(error)
    )
  }

  onClickRadio(data: any){
    var s = 0
    Object.keys(data).forEach(k => {
      if(data[k] == "1") s++;
    });
    Swal.fire(
      'Quiz done',
      "You got " + s + " questions right",
      'success'
    ).then( () =>
      this.quizService.postQuizResults(this.quizId, s).subscribe(
        res => {
          console.log(res)
        },
        err => {
          console.log(err)
        })
    )
  }
}
