export class ForumPostModel {
    public id: number;
    public courseId: number;
    public userId: number;
    public text: string;
    public title: string;

    constructor(postId: number, courseId: number, userId: number, text: string, subject: string){
        this.id = postId;
        this.courseId = courseId;
        this.userId = userId;
        this.text = text;
        this.title = subject;
    }
}