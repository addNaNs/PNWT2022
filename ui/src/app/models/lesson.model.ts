export class LessonModel {
    public id: number;
    public name: string;
    public text: string;

    constructor(courseId: number, name: string, text: string){
        this.id = courseId;
        this.name = name;
        this.text = text;
    }
}