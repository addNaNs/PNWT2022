import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserModel } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
import { ForumService } from '../forum.service';

@Component({
  selector: 'app-add-forum-post',
  templateUrl: './add-forum-post.component.html',
  styleUrls: ['./add-forum-post.component.css']
})
export class AddForumPostComponent implements OnInit {
  forumPost : any;
  courseId = this.route.snapshot.params['courseId'];
  post : any;
  user_id : any;
  userList: UserModel[] = [];
  user : any;

  constructor(private router : Router, private forumService : ForumService, private route: ActivatedRoute, 
    private userService: UserService) { }

  ngOnInit(): void {
    let jwt = localStorage.getItem("token");
    if(jwt!=null){
      let jwtData = jwt.split('.')[1]
      let decodedJwtJsonData = window.atob(jwtData)
      let decodedJwtData = JSON.parse(decodedJwtJsonData)
      this.user_id = decodedJwtData;
      console.log(decodedJwtData)
    }
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
  }
  onClickAddPostForm(data: any){
    this.post = {
      "user_id" :this.user.id,
      "title" : data.subject,
      "text" : data.message,
      "course_id" : this.courseId
    }
    console.log(this.post)
    this.forumService.postForumPost(this.post);
  }
  addPost(){
    // this.forumService.postForumPost().subscribe(
    //   (response : ForumModel) => {
        
    //   }
    // )
    Swal.fire(
      'Good job!',
      'Successfully added new post to our forum page!',
      'success'
    ).then(()=>{
      this.router.navigate(['/forum']).then(() => window.location.reload())

    }
    )
  }

}
