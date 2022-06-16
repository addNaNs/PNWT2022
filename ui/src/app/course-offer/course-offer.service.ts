import { Component, Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser'; 
import {CourseOfferComponent} from './course-offer.component'
//import {Http} from '@angular/common/http';

import { HttpClientModule } from '@angular/common/http';
import { Observable } from "rxjs";


@Injectable({
    providedIn:'root'
})


export class CourseOfferHomeService{
    constructor(private http : HttpClient){
    }

    // public getItemsFromShop():Observable<Item[]>{
    //        return this.http.get<Item[]>('https://localhost:44392/api/Item'); 
    // }

     courses = [
        {
            itemId : '2D59820A-4F78-455F-97F1-0090D2605953',
            name: 'Mathematical logic and computability theory',
            description: 'Computability theory, also known as recursion theory, is a branch of mathematical logic, computer science, and the theory of computation that originated in the 1930s with the study of computable functions and Turing degrees.',
            imagePath: "assets/images/mathematics.png"
        },
        {
            itemId : 'E5FA3CEF-F4F8-4D15-8E2D-61D13944BD1E',
            name: 'Advanced web technologies',
            description: 'This course introduces concepts, principles and methods in current client and server-side Web technologies. Basic Web technologies such as HTML, HTTP, CSS, XML, JavaScript etc. are a prerequisite to take this course. The focus of this course is rather on advanced topics in emerging Web technologies.',
            imagePath: "assets/images/mathematics.png"
        },
        {
            itemId: '0B02C733-B49E-42AC-98A7-B1E62FD35294',
            name: 'Computer networks',
            description: 'Computer networking involves many things coming together, and there are many challenges and important problems to solve in the field of networking: Scaling hardware and software to very high (e.g., 100+ Gbps) speeds (routers, switches) Effective interaction with user (web technologies)',
            imagePath: "assets/images/web-technologies.jpg"
        },
        {
            itemId: 'AE8A2DAB-E543-4910-98C6-13AFF053670C',
            name: 'Mathematics I',
            description: 'Technical Mathematics covers material designed for career technical or general studies for students who need to study particular mathematical topics. Topics will include measurement, algebra, geometry, trigonometry, graphs and finance. These are presented on an introductory level and the emphasis is on applications.',
            imagePath: "assets/images/mathematics-3.jpg"
        }
    ];
    getItems(){
        return this.courses;
    }
    getItem(itemId: string){
        const item = this.courses.find(
            (it1)=>{
                return it1.itemId === itemId; 
            }
        )
        return item;
    }
}