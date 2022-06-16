import { Component, Injectable } from "@angular/core";
import {HttpClient, HttpEvent, HttpResponse} from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser'; 
import { HttpClientModule } from '@angular/common/http';
import { Observable } from "rxjs";
import { connectableObservableDescriptor } from "rxjs/internal/observable/ConnectableObservable";
import  Swal from "sweetalert2";
import { UserModel } from "./userModel";


@Injectable({
    providedIn:'root'
})


export class SignInService{

    public static token  : string;

    constructor(private http : HttpClient){}

    
    public getUsers():Observable<UserModel[]>{
        return this.http.get<UserModel[]>('https://localhost:44392/api/user');
    }

    public login(username:string, password:string):Observable<any>{
        var url = 'http://localhost:8070/course-app/user/login'
        var body = JSON.stringify({"username":username, "password":password})

        console.log(body)

        return this.http.post<any[]>(
            url,
            body, 
            {
                headers : {"Content-Type": "application/json"},
            }
        )
    }
}

