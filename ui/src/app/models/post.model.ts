export class PostModel {
    public userId: number;
    public text: string;
    public title: string;

    constructor(userId: number, text: string, subject: string){
        this.userId = userId;
        this.text = text;
        this.title = subject;
    }
}