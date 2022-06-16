export class PostReplyPostModel {
    public id: number;
    public userId: number;
    public text: string;

    constructor(postId: number, userId: number, text: string){
        this.id = postId;
        this.userId = userId;
        this.text = text;
    }
}