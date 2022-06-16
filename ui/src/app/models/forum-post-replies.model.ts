import { ForumPostModel } from "./forum.model";

export class ForumPostRepliesModel {
    public id: number;
    public post: ForumPostModel;
    public userId: number;
    public text: string;

    constructor(id: number, post: ForumPostModel, userId: number, text: string){
        this.id = id;
        this.post = post;
        this.userId = userId;
        this.text = text;
    }
}