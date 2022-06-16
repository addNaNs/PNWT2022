export class PostPostModel {
    public courseId: number;
    public userId: number;
    public text: string;
    public title: string;

    constructor(courseId: number, userId: number, text: string, subject: string){
        this.courseId = courseId;
        this.userId = userId;
        this.text = text;
        this.title = subject;
    }
}