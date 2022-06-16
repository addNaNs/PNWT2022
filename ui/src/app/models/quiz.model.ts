import { QuestionQuizModel } from "./question-quiz.model";

export class QuizModel {
    public id: number;
    public title: string;
    public nQuestions: number;
    public questions: QuestionQuizModel[];

    constructor(id: number, title: string, nQuestions: number, questions: QuestionQuizModel[]){
        this.id = id;
        this.title = title;
        this.nQuestions = nQuestions;
        this.questions = questions;
    }
}