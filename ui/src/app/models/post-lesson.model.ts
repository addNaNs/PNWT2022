export class PostLessonModel {
    public course_id: number;
    public name: string;
    public text: string;

    constructor(courseId: number, name: string, text: string){
        this.course_id = courseId;
        this.name = name;
        this.text = text;
    }
}