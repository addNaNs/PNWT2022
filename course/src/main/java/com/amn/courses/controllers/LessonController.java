package com.amn.courses.controllers;

import com.amn.courses.dto.CourseRepository;
import com.amn.courses.dto.LessonRepository;
import com.amn.courses.grpc.GrpcClient;
import com.amn.courses.models.Course;
import com.amn.courses.models.Lesson;
import com.amn.courses.models.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netflix.discovery.converters.Auto;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/lesson")
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping(path="")
    public  @ResponseBody
    ResponseEntity<Object> addNewLesson (@RequestBody ObjectNode json) {
        String name = json.get("name").asText();
        String text = json.get("text").asText();
        Integer courseId = json.get("course_id").asInt();

        if(courseId == null || courseRepository.findById(courseId).isEmpty()){
            JSONObject entity = new JSONObject();
            entity.put("message","No course with that ID");
            return new ResponseEntity<Object>(entity, HttpStatus.BAD_REQUEST);
        }
        Course course = courseRepository.findById(courseId).get();

        Lesson lesson = new Lesson();
        lesson.setName(name);
        lesson.setText(text);
        lesson.setCourse(course);

        lessonRepository.save(lesson);
        JSONObject entity = new JSONObject();
        entity.put("message","Saved");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody Iterable<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @GetMapping(path="{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> getLesson(@PathVariable(value="id") Integer id) {
        // This returns a JSON or XML with the users
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if(lesson.isEmpty()) {
            JSONObject entity = new JSONObject();
            entity.put("message","Not Found");
            return new ResponseEntity<Object>(entity,HttpStatus.NOT_FOUND);
        }
        else {
            return ResponseEntity.ok(lesson);
        }
    }

    @DeleteMapping(path="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteLesson(@PathVariable(value="id") Integer id) {
        lessonRepository.deleteById(id);
        JSONObject entity = new JSONObject();
        entity.put("message","Deleted");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

    @PutMapping(path="{id}")
    public @ResponseBody Object updateLesson(@PathVariable(value="id") Integer id, @RequestBody Lesson updatedLesson){
        Lesson lesson = lessonRepository.findById(id).get();
        if(lesson == null){
            JSONObject entity = new JSONObject();
            entity.put("message","No lesson with that ID");
            return new ResponseEntity<Object>(entity,HttpStatus.BAD_REQUEST);
        }

        if(updatedLesson.getName() != null)
            lesson.setName(updatedLesson.getName());
        if(updatedLesson.getText() != null)
            lesson.setText(updatedLesson.getText());
        if(updatedLesson.getCourse() != null)
            lesson.setCourse(updatedLesson.getCourse());
        
        lessonRepository.save(lesson);

        JSONObject entity = new JSONObject();
        entity.put("message","Updated");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }
}
