export class ReplyModel {
    public id: number;
    public userId: number;
    public courseId: number;
    public title: string;
    public text: string;
    // public replies : [];

    constructor(postId: number, userId: number, courseId: number, text: string, subject: string){
        this.id = postId;
        this.userId = userId;
        this.courseId = courseId;
        this.text = text;
        this.title = subject;
    }
}