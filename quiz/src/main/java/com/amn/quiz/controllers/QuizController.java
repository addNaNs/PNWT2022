package com.amn.quiz.controllers;

import com.amn.quiz.dto.QuestionRepository;
import com.amn.quiz.dto.QuizRepository;
import com.amn.quiz.models.Question;
import com.amn.quiz.models.Quiz;
import com.amn.quiz.models.Attempt;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

@Controller
@RequestMapping(path = "/quiz")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path = "")
    public @ResponseBody ResponseEntity<Object> addNewQuiz(@RequestBody Quiz quiz){
        try{
            var r = restTemplate.getForObject(
                    "https://course-app/course/" + quiz.getCourse_id().toString(),
                    String.class
            );
            quizRepository.save(quiz);
        } catch (HttpServerErrorException.InternalServerError e){
            JSONObject entity = new JSONObject();
            entity.put("message","No course with that ID");
            return new ResponseEntity<Object>(entity,HttpStatus.BAD_REQUEST);
        }

        JSONObject entity = new JSONObject();
        entity.put("message","Saved");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody Iterable<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @GetMapping(path="{id}")
    public @ResponseBody Quiz getQuiz(@PathVariable(value="id") Integer id) {
        return quizRepository.findById(id).get();
    }

    @GetMapping(path="course/{id}")
    public @ResponseBody Iterable<Quiz> getQuizzesByCourse(@PathVariable(value="id") Integer id) {
        Iterable<Quiz> allQuizzes = quizRepository.findAll();
        List<Quiz> filteredQuizzes = new ArrayList<>();
        for (Quiz quiz : allQuizzes) {
            if(quiz.getCourse_id() == id){
                filteredQuizzes.add(quiz);
            }
        }
        return filteredQuizzes;
    }


    @DeleteMapping(path="{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteQuiz(@PathVariable(value="id") Integer id) {
        quizRepository.deleteById(id);
        JSONObject entity = new JSONObject();
        entity.put("message","Deleted");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

    @PostMapping(path="attempt/{id}")
    public @ResponseBody ResponseEntity<Object> attemptQuiz(@PathVariable(value="id") Integer id) {
        Quiz quiz = quizRepository.findById(id).get();

        if(quiz.getQuestions().size() < quiz.getNQuestion()){
            JSONObject entity = new JSONObject();
            entity.put("message","Not enough questions provided");
            return new ResponseEntity<Object>(entity,HttpStatus.BAD_REQUEST);
        }

        List<Question> res = quiz.getQuestions().stream().collect(Collectors.toList());
        Collections.shuffle(res);
        List<Question> result = new ArrayList<>();
        for (int i = 0; i < quiz.getnQuestion(); i++) {
            result.add(res.get(i));
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questions", result);
        return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
    }
}
