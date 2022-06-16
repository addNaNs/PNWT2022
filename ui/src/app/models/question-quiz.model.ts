export class QuestionQuizModel {
    public id: number;
    public text: string;
    public correctAnswer: string;
    public wrongAnswer1: string;
    public wrongAnswer2: string;
    public wrongAnswer3: string;

    constructor(id: number, text: string, correctAnswer: string, wrongAnswer1: string, wrongAnswer2: string, wrongAnswer3: string){
        this.id = id;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }
}