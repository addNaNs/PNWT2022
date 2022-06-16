import { UserModel } from "./user.model";

export class CourseListModel {
    public id: number;
    public name: string;
    public instructor: UserModel;
    public description: string;
    public users? : any

    constructor(courseId: number, name: string, instructor: UserModel, description: string){
        this.id = courseId;
        this.name = name;
        this.instructor = instructor;
        this.description = description;
    }
}