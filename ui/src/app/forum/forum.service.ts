import { Component, Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule }    from '@angular/platform-browser'; 
//import {Http} from '@angular/common/http';

import { Observable } from "rxjs";
import { CourseListModel } from "../models/course-list.model";
import Swal from "sweetalert2";
import { ForumPostModel } from "../models/forum.model";
import { ReplyModel } from "../models/reply.model";
import { PostReplyPostModel } from "../models/post-reply-post.model";
import { Globals } from "../globals";
import { ForumPostRepliesModel } from "../models/forum-post-replies.model";
import { PostPostModel } from "../models/post-post.model";


@Injectable({
    providedIn:'root'
})


export class ForumService{
    constructor(private http : HttpClient){
    }

    public getCoursesFromDB():Observable<CourseListModel[]>{
           return this.http.get<CourseListModel[]>('http://localhost:8070/course-app/course'); 
    }
    public postForumPost(dataForum: PostPostModel): Observable<PostPostModel>{
        const headers = { 
            'content-type': 'application/json',
            'Authorization': "Bearer " + Globals.user.token
        }  
        const body=JSON.stringify(dataForum);
        console.log(body);

        var tmp = this.http.post<PostPostModel>('http://localhost:8070/forum-app/post', body, {'headers': headers}); 
        console.log(tmp);
        tmp.subscribe(
          res => {
              Swal.fire(
                  'Good job!',
                  'You have successfully posted new post!',
                  'success'
              );
              console.log(res)
          },
          err => {console.log(err); alert(err)}
        );
        return tmp;
    }

    public getPostsForCourse(id: number): Observable<ForumPostModel[]>{
        return this.http.get<ForumPostModel[]>('http://localhost:8070/forum-app/post/course/' + id); 
    }

    public getCourseByCourseId(id: number):Observable<CourseListModel>{
        return this.http.get<CourseListModel>('http://localhost:8070/course-app/course/' + id); 
    }

    public getPostForCourse(courseId: number): Observable<ReplyModel>{
        return this.http.get<ReplyModel>('http://localhost:8070/forum-app/post/course/' + courseId); 
    }
    public getRepliesForCourse(courseId: number): Observable<ReplyModel>{
        return this.http.get<ReplyModel>('http://localhost:8070/forum-app/post/course/' + courseId); 
    }
    public getRepliesForSpecificPost(postId: number): Observable<ForumPostRepliesModel[]>{
        return this.http.get<ForumPostRepliesModel[]>('http://localhost:8070/forum-app/reply/post/' + postId); 
    }

    public postReplyToPost(data : PostReplyPostModel): Observable<PostReplyPostModel>{
        const headers = { 
            'content-type': 'application/json',
            'Authorization': "Bearer " + Globals.user.token
        }  
        const body=JSON.stringify(data);
        console.log(body);

        var tmp = this.http.post<PostReplyPostModel>('http://localhost:8070/forum-app/reply', body, {'headers': headers}); 
        console.log(tmp);
        tmp.subscribe(
          res => {
              Swal.fire(
                  'Good job!',
                  'You have successfully replied to post!',
                  'success'
              );
              console.log(res)
          },
          err => {console.log(err); alert(err)}
        );
        return tmp;
    }
}