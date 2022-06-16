import { LessonModel } from "./lesson.model";
import { UserModel } from "./user.model";

export class LessonsModel {
    public id: number;
    public name: string;
    public instructor: UserModel;
    public users : UserModel[];
    public description: string;
    public lessons: LessonModel[];

    constructor(courseId: number, name: string, instructor: UserModel, users: UserModel[], description: string, lessons: LessonModel[]){
        this.id = courseId;
        this.name = name;
        this.instructor = instructor;
        this.users = users;
        this.description = description;
        this.lessons = lessons;
    }
}