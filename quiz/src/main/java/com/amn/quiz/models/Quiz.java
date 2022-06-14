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

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private Set<Attempt> attempts;

    @JsonIgnoreProperties({"questions", "attempts"})
    @OneToMany(mappedBy = "quiz")
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

    public Set<Attempt> getScores() {
        return attempts;
    }

    public void setScores(Set<Attempt> attempts) {
        this.attempts = attempts;
    }
}
