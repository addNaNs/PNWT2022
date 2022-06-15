package com.amn.quiz.controllers;

import com.amn.quiz.dto.QuestionRepository;
import com.amn.quiz.dto.QuizRepository;
import com.amn.quiz.models.Question;
import com.amn.quiz.models.Quiz;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netflix.discovery.converters.Auto;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @PostMapping(path = "")
    public @ResponseBody
    ResponseEntity<Object> addNewQuestion(@RequestBody ObjectNode json){

        Question question = new Question();
        if(json.get("text") != null) question.setText(json.get("text").asText());
        if(json.get("correctAnswer") != null) question.setCorrectAnswer(json.get("correctAnswer").asText());
        if(json.get("wrongAnswer1") != null) question.setWrongAnswer1(json.get("wrongAnswer1").asText());
        if(json.get("wrongAnswer2") != null) question.setWrongAnswer2(json.get("wrongAnswer2").asText());
        if(json.get("wrongAnswer3") != null) question.setWrongAnswer3(json.get("wrongAnswer3").asText());
        if(json.get("quiz_id") != null) question.setQuiz(quizRepository.findById(json.get("quiz_id").asInt()).get());

        questionRepository.save(question);

        JSONObject entity = new JSONObject();
        entity.put("message","Saved");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

    @GetMapping(path="")
    public @ResponseBody Iterable<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping(path="{id}")
    public @ResponseBody Question getQuestion(@PathVariable(value="id") Integer id) {
        return questionRepository.findById(id).get();
    }

    @GetMapping(path="quiz/{id}")
    public @ResponseBody Iterable<Question> getQuizzesByCourse(@PathVariable(value="id") Integer id) {
        Iterable<Question> allQuestions = questionRepository.findAll();
        List<Question> filteredQuestions = new ArrayList<>();
        for (Question question : allQuestions) {
            if(question.getQuiz() == null){
                continue;
            }
            if(question.getQuiz().getId() == id){
                filteredQuestions.add(question);
            }
        }
        return filteredQuestions;
    }

    @DeleteMapping(path="{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> deleteQuestion(@PathVariable(value="id") Integer id) {
        questionRepository.deleteById(id);
        JSONObject entity = new JSONObject();
        entity.put("message","Deleted");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

    @PutMapping(path="{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Object> updateQuestion(@PathVariable(value="id") Integer id, @RequestBody ObjectNode json){

        Question question = questionRepository.findById(id).get();
        if(json.get("text") != null) question.setText(json.get("text").asText());
        if(json.get("correctAnswer") != null) question.setCorrectAnswer(json.get("correctAnswer").asText());
        if(json.get("wrongAnswer1") != null) question.setWrongAnswer1(json.get("wrongAnswer1").asText());
        if(json.get("wrongAnswer2") != null) question.setWrongAnswer2(json.get("wrongAnswer2").asText());
        if(json.get("wrongAnswer3") != null) question.setWrongAnswer3(json.get("wrongAnswer3").asText());
        if(json.get("quiz_id") != null) question.setQuiz(quizRepository.findById(json.get("quiz_id").asInt()).get());

        questionRepository.save(question);

        JSONObject entity = new JSONObject();
        entity.put("message","Saved");
        return new ResponseEntity<Object>(entity,HttpStatus.OK);
    }

}
