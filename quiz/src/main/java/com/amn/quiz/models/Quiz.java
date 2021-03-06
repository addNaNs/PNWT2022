package com.amn.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private Integer course_id;
    private Integer nQuestion;


    @JsonIgnoreProperties({"quiz"})
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Attempt> attempts;

    @JsonIgnoreProperties({"quiz"})
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Question> questions;

    public Quiz() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Integer getNQuestion() {
        return nQuestion;
    }

    public void setNQuestion(Integer nQuestion) {
        this.nQuestion = nQuestion;
    }

    public Integer getnQuestion() {
        return nQuestion;
    }

    public void setnQuestion(Integer nQuestion) {
        this.nQuestion = nQuestion;
    }

    public Set<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(Set<Attempt> attempts) {
        this.attempts = attempts;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
