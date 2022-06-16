import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CoursesListService } from '../courses-list.service';

@Component({
  selector: 'app-add-lesson',
  templateUrl: './add-lesson.component.html',
  styleUrls: ['./add-lesson.component.css']
})
export class AddLessonComponent implements OnInit {
  courseId = this.route.snapshot.params["courseId"];

  constructor(private route: ActivatedRoute, private courseService: CoursesListService) { }

  ngOnInit(): void {
  }

  onClickAddNewLessonForm(data: any){
    var postLesson = {
      "course_id": this.courseId,
      "name": data.txtLesson,
      "text": data.txtDescription
    }
    console.log(postLesson);
    this.courseService.postNewLesson(postLesson);
  }

}
