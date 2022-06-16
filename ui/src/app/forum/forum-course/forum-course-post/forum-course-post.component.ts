import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { Globals } from 'src/app/globals';
import { ForumPostRepliesModel } from 'src/app/models/forum-post-replies.model';
import { ForumPostModel } from 'src/app/models/forum.model';
import { UserModel } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
import { ForumService } from '../../forum.service';

@Component({
  selector: 'app-forum-course-post',
  templateUrl: './forum-course-post.component.html',
  styleUrls: ['./forum-course-post.component.css']
})
export class ForumCoursePostComponent implements OnInit {

  onePost : any;
  postsForCourse : ForumPostModel[] = [];
  showAnswerForm : boolean = false;
  dataPostReply: any;
  user_id : any;
  userList: UserModel[] = [];
  user : any;
  allReplies : ForumPostRepliesModel[] = []
  
  constructor(private route : ActivatedRoute, private forumService: ForumService, private userService: UserService) { }

  ngOnInit(): void {
    const courseId = this.route.snapshot.params['courseId'];
    const postId = this.route.snapshot.params['postId'];

    console.log(courseId);
    console.log(postId);

    let jwt = localStorage.getItem("token");
    if(jwt!=null){
      let jwtData = jwt.split('.')[1]
      let decodedJwtJsonData = window.atob(jwtData)
      let decodedJwtData = JSON.parse(decodedJwtJsonData)
      this.user_id = decodedJwtData;
      console.log(decodedJwtData)
    }

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
         
        for (var i = 0; i < this.postsForCourse.length; i++){
          var post = {
            id : this.postsForCourse[i].id,
            courseId: this.postsForCourse[i].courseId,
            userId: this.postsForCourse[i].userId,
            text: this.postsForCourse[i].text,
            title: this.postsForCourse[i].title
          }
          if(post.courseId == courseId && post.id == postId){
            console.log(post);
    
            this.onePost = post;
            console.log(this.onePost);
            break;
          }
        }
      },
      (error : any)=>console.log(error)
    )
    this.userService.getUsers().subscribe(
      (response : UserModel[]) =>{
        for(var i=0; i<response.length; i++){
          var user = {
            id : response[i].id,
            name : response[i].name,
            password : response[i].password,
            username : response[i].username,
            email : response[i].email
          }
          this.userList = [...this.userList, user];
        }
        for(var i=0; i<this.userList.length; i++){
          if(this.userList[i].username==this.user_id.sub){
            this.user = this.userList[i];
            console.log(this.user);
            break;
          }
        }
      },
      (error : any)=>console.log(error)
    )

    this.forumService.getRepliesForSpecificPost(postId).subscribe(
      (response: ForumPostRepliesModel[]) => {
        for(var i=0; i<response.length; i++){
          var reply = {
            id : response[i].id,
            text : response[i].text,
            userId : response[i].userId,
            post : response[i].post
          }
          console.log(reply);
          this.allReplies = [...this.allReplies, reply];
        }
      },
      (error : any)=>console.log(error)
    )
  }
  replyToPost(){
    this.showAnswerForm = true;
  }
  onClickReplyForm(data: any){
    // let user = localStorage.getItem("name"),



    this.dataPostReply = {
      "post_id" : this.onePost.id,
      "user_id" : this.user.id,
      "text" :  data.replyPostTextId
    }
    console.log(this.dataPostReply);
    this.forumService.postReplyToPost(this.dataPostReply);
     
  }

}
