import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ForumPostModel } from 'src/app/models/forum.model';
import { PostReplyPostModel } from 'src/app/models/post-reply-post.model';
import Swal from 'sweetalert2';
import { ForumService } from '../forum.service';

@Component({
  selector: 'app-forum-course',
  templateUrl: './forum-course.component.html',
  styleUrls: ['./forum-course.component.css']
})
export class ForumCourseComponent implements OnInit {
  postsForCourse : ForumPostModel[] = [];
  // showAnswerForm : boolean = false;
  postReply : any;
  courseId = this.route.snapshot.params['courseId'];
  postId = this.route.snapshot.params['id'];


  constructor(private route : ActivatedRoute, private forumService : ForumService, private router : Router) { }

  ngOnInit(): void {
    const courseId = this.route.snapshot.params['courseId'];
    const postId = this.route.snapshot.params['postId'];

    console.log(courseId);
    console.log(postId);


    this.forumService.getPostsForCourse(courseId).subscribe(
      (response : ForumPostModel[]) => {
        for(var i = 0; i < response.length; i++){
          var post = {
            id : response[i].id,
            courseId: response[i].courseId,
            userId: response[i].userId,
            text: response[i].text,
            title: response[i].title
          }
          console.log(post);
          this.postsForCourse = [...this.postsForCourse, post];
        }
      },
      (error : any)=>console.log(error)
    )
  }

  replyToPost() {
    // this.showAnswerForm = true;
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Please click on the title of the post to reply!',
      // footer: '<a href="/sigIn">Click on the header - login if you have accout. Otherwise, use register.</a>'
    })
    // alert("Answering to post!")
  }
}
