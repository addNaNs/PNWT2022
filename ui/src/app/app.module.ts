import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from "@angular/router/testing";


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccountComponent } from './header/account/account.component';
import { DescriptionComponent } from './home/home-page-center/description/description.component';
import { ElearningLogoComponent } from './header/elearning-logo/elearning-logo.component';
import { SearchComponent } from './home/home-page-center/search/search.component';
import { SearchedComponent} from './search/search.component'
import { ProfessorsAssistantsComponent } from './professors-assistants/professors-assistants.component';
import { AboutComponent } from './about/about.component';
import { SignInComponent } from './header/account/sign-in/sign-in.component';
import { RegisterComponent } from './header/account/register/register.component';
import { HomePageCenterComponent } from './home/home-page-center/home-page-center.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { CategoryComponent } from './category/category.component';
import { FormsModule }   from '@angular/forms';
import { FooterComponent } from './footer/footer.component';
import { CourseOfferComponent } from './course-offer/course-offer.component';
import { CoursesListComponent } from './courses-list/courses-list.component';
import { CourseItemComponent } from './courses-list/course-item/course-item.component';
import { ForumComponent } from './forum/forum.component';
import { AddForumPostComponent } from './forum/add-forum-post/add-forum-post.component';
import { ForumCourseComponent } from './forum/forum-course/forum-course.component';
import { ForumCoursePostComponent } from './forum/forum-course/forum-course-post/forum-course-post.component';
import { MyCoursesComponent } from './my-courses/my-courses.component';
import { LessonComponent } from './courses-list/lesson/lesson.component';
import { QuizComponent } from './quiz/quiz.component';
import { AddLessonComponent } from './courses-list/add-lesson/add-lesson.component';


const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'courses', component: CoursesListComponent},
  {path: 'courses/:courseId', component: CourseItemComponent},
  {path: 'professors-assistants', component: ProfessorsAssistantsComponent},
  {path: 'about', component: AboutComponent},
  {path: 'my-courses', component: MyCoursesComponent},
  {path: 'forum', component: ForumComponent},
  {path: ':courseId/add-forum', component: AddForumPostComponent},
  {path: 'forum/:courseId', component: ForumCourseComponent},
  {path: 'forum/:courseId/:postId', component: ForumCoursePostComponent},
  {path: ':courseId/:lessonId', component: LessonComponent},
  {path: 'quiz/:courseId/:quizId', component: QuizComponent},
  {path: 'courses/:courseId/add-lesson', component: AddLessonComponent},

  {path: 'register', component: RegisterComponent},
  {path: 'sigIn', component: SignInComponent},
  {path: 'search/:query', component: SearchedComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    AccountComponent,
    DescriptionComponent,
    ElearningLogoComponent,
    SearchComponent,
    ProfessorsAssistantsComponent,
    AboutComponent,
    SignInComponent,
    RegisterComponent,
    HomePageCenterComponent,
    HeaderComponent,
    HomeComponent,
    CategoryComponent,
    SearchedComponent,
    FooterComponent,
    CourseOfferComponent,
    CourseItemComponent,
    CoursesListComponent,
    ForumComponent,
    AddForumPostComponent,
    ForumCourseComponent,
    ForumCoursePostComponent,
    MyCoursesComponent,
    LessonComponent,
    QuizComponent,
    AddLessonComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    RouterTestingModule,
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
