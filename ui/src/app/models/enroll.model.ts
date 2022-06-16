export class EnrollModel {
    public course_id: number;
    public user_id: number;

    constructor(courseId: number, userId: number){
        this.course_id = courseId;
        this.user_id = userId;
    }
}