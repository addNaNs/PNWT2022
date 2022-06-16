import { CourseListModel } from "./course-list.model"

export class EnrolledModel {
    public id: number;
    public name: string;
    public email: string;
    public password: string;
    public courses: CourseListModel[];

    constructor(id: number, name: string, email: string, password: string, courses: CourseListModel[]){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.courses = courses;
    }
}