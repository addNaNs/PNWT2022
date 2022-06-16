import { Component, Injectable } from "@angular/core";
import {HttpClient, HttpEvent, HttpResponse} from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser'; 
import { HttpClientModule } from '@angular/common/http';
import { Observable } from "rxjs";
import { connectableObservableDescriptor } from "rxjs/internal/observable/ConnectableObservable";
import  Swal from "sweetalert2";


@Injectable({
    providedIn:'root'
})


export class RegisterService{

    public static token  : string;

    constructor(private http : HttpClient){}

    public register(name:string, username:string, email:string, password:string):Observable<any>{
        var url = 'http://localhost:8070/course-app/user/register'
        var body = JSON.stringify({
            "username":username, 
            "password":password,
            "email":email,
            "name":name,

        })

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

