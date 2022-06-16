package com.amn.courses.controllers;

import com.amn.courses.dto.CourseRepository;
import com.amn.courses.dto.UserRepository;
import com.amn.courses.grpc.GrpcClient;
import com.amn.courses.misc.JWTUtil;
import com.amn.courses.models.Course;
import com.amn.courses.models.User;
import com.amn.courses.util.QuizMessage;
import com.amn.courses.util.RabbitConfig;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Claims;
import net.minidev.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping(path = "/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(path="", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> addNewCourse (
            @RequestHeader(name="Authorization", required = false) String auth,
            @RequestBody ObjectNode json
    ) {
        String name = json.get("name").asText();
        Integer instructorId;
        if(json.get("instructorId") != null){
            instructorId = json.get("instructorId").asInt();
        } else {
            Claims x = jwtUtil.getAllClaimsFromToken(auth.split(" ")[1]);
            instructorId = (Integer) x.get("user_id");
        }

        if(userRepository.findById(instructorId).isEmpty()){
            JSONObject entity = new JSONObject();
            entity.put("message","No instructor with that ID");
            GrpcClient.log("Course", "Create", "Fail");
            return new ResponseEntity<Object>(entity,HttpStatus.BAD_REQUEST);
        }
        Course n = new Course();
        n.setName(name);
        n.setInstructor(userRepository.findById(instructorId).get());
        if(json.get("description") != null) n.setDescription(json.get("description").asText());

        if(userRepository.findById(instructorId).isEmpty()){
            JSONObject entity = new JSONObject();
            entity.put("message","No instructor with that ID!");
            GrpcClient.log("Course", "Create", "Fail");
            return new ResponseEntity<Object>(entity,HttpStatus.BAD_REQUEST);
        }

        courseRepository.save(n);
        JSONObject entity = new JSONObject();
        entity.put("message","Saved");
        GrpcClient.log("Course", "Create", "Success");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody Iterable<Course> getAllCourses() {
        GrpcClient.log("Course", "Get all", "Success");
        return courseRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Course getCourse(@PathVariable(value="id") Integer id) {
        GrpcClient.log("Course", "Get single", "Success");
        return courseRepository.findById(id).get();
    }

    @PutMapping(path="")
    public @ResponseBody Object updateCourse(@RequestBody Course course){
        Course c = courseRepository.findById(course.getId()).get();
        if(c == null){
            JSONObject entity = new JSONObject();
            entity.put("message","No course with that ID");
            GrpcClient.log("Course", "Update", "Fail");
            return new ResponseEntity<Object>(entity,HttpStatus.BAD_REQUEST);
        }

        if(course.getName() != null)
            c.setName(course.getName());
        if(course.getDescription() != null)
            c.setDescription(course.getDescription());

        courseRepository.save(c);

        GrpcClient.log("Course", "Update", "Success");
        JSONObject entity = new JSONObject();
        entity.put("message","Updated");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }


    @DeleteMapping(path="{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteCourse(@PathVariable(value="id") Integer id) {

        QuizMessage quizMessage = new QuizMessage();
        quizMessage.setCourse_id(id);
        quizMessage.setSuccess(true);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, quizMessage);
        courseRepository.deleteById(id);

        GrpcClient.log("Course", "Delete", "Success");
        JSONObject entity = new JSONObject();
        entity.put("message","Deleted");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

    @PostMapping(path = "/enroll", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> enrollToCourse(
            @RequestHeader(name="Authorization", required = false) String auth,
            @RequestBody ObjectNode json
    ){
        Integer user_id = null;
        if(json.get("user_id") != null){
            user_id = json.get("user_id").asInt();
        } else {
            Claims x = jwtUtil.getAllClaimsFromToken(auth.split(" ")[1]);
            user_id = (Integer) x.get("user_id");
        }
        Integer course_id = json.get("course_id").asInt();
        Optional<User> u = userRepository.findById(user_id).stream().findFirst();
        User user;
        if(u.isEmpty()){
            JSONObject entity = new JSONObject();
            entity.put("message","No user with that ID");
            GrpcClient.log("Course", "Enroll", "Fail");
            return new ResponseEntity<Object>(entity,HttpStatus.BAD_REQUEST);
        }
        user = u.get();

        Optional<Course> c = courseRepository.findById(course_id).stream().findFirst();
        Course course;
        if(c.isEmpty()){
            JSONObject entity = new JSONObject();
            entity.put("message","No course with that ID");
            GrpcClient.log("Course", "Enroll", "Fail");
            return new ResponseEntity<Object>(entity,HttpStatus.BAD_REQUEST);
        }
        course = c.get();
        for (Course c1 : user.getCourses()) {
            if(c1.equals(course)) {
                JSONObject entity = new JSONObject();
                entity.put("message","User is already enrolled");
                GrpcClient.log("Course", "Enroll", "Fail");
                return new ResponseEntity<Object>(entity,HttpStatus.BAD_REQUEST);
            }
        }
        user.getCourses().add(course);
        userRepository.save(user);
        JSONObject entity = new JSONObject();
        entity.put("message","Enrolled");
        GrpcClient.log("Course", "Enroll", "Success");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

}
