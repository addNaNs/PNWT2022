package com.amn.courses.controllers;

import com.amn.courses.models.Course;
import com.amn.courses.models.User;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseControllerTest {

    public CourseController courseController;

    @BeforeEach
    void setup(){
        courseController = new CourseController();
    }

    @Test
    void addNewCourse() {
        User user1 = new User(1, "Profesor1", "prof1", "prof1@etf.unsa.ba", "prof1***3Rm!");

        Course course = new Course();
        course.setName("Course 1");
        course.setInstructor(user1);
    }

    @Test
    void getAllCourses() {
    }

    @Test
    void deleteCourse() {
    }

    @Test
    void enrollToCourse() {
    }
}