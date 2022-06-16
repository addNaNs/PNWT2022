import { Component, Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';

import { Observable } from "rxjs";
import Swal from "sweetalert2";
import { UserModel } from "../models/user.model";
import { EnrollModel } from "../models/enroll.model";
import { Globals } from "../globals";
import { EnrolledModel } from "../models/enrolled.model";


@Injectable({
    providedIn:'root'
})


export class UserService{
    constructor(private http : HttpClient){
    }

    public getUsers():Observable<UserModel[]>{
           return this.http.get<UserModel[]>('http://localhost:8070/course-app/user'); 
    }
    public postUserEnrollCourse(data : any){
        const headers = { 
            'content-type': 'application/json',
            'Authorization': "Bearer " + Globals.user.token
        }
        const body=JSON.stringify(data);
        console.log(body);

        var tmp = this.http.post<any>('http://localhost:8070/course-app/course/enroll', body, {'headers': headers}); 
        console.log(tmp);
        return tmp;
    }
    public getAllUsers():Observable<EnrolledModel[]>{
        return this.http.get<EnrolledModel[]>('http://localhost:8070/course-app/user'); 
 }

}