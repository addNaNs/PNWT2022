import { QuestionQuizModel } from "./question-quiz.model";

export class ReturningQuizModel {
    public questions: QuestionQuizModel[];

    constructor(questions: QuestionQuizModel[]){
        this.questions = questions;
    }
}