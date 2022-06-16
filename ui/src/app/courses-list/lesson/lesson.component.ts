import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LessonModel } from 'src/app/models/lesson.model';
import { CoursesListService } from '../courses-list.service';

@Component({
  selector: 'app-lesson',
  templateUrl: './lesson.component.html',
  styleUrls: ['./lesson.component.css']
})
export class LessonComponent implements OnInit {

  constructor(private courseService: CoursesListService, private route: ActivatedRoute) { }
  lesson : any;

  ngOnInit(): void {
    const lessonId = this.route.snapshot.params["lessonId"];
    this.courseService.getLessonsByLessonId(lessonId).subscribe(
      (res : LessonModel) => {
        var lessonFromDB = {
          id: res.id,
          name: res.name,
          text: res.text
        }
        this.lesson = lessonFromDB;
        console.log(this.lesson);
      }
    )
  }

}
