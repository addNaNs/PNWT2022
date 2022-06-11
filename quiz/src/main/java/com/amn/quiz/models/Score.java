package com.amn.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnoreProperties({"instances"})
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private Integer user_id;

    private Integer points;
    private boolean passed;

    public Score() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
        if(points >= quiz.getMax_points() * quiz.getPass_percent()){
            this.passed = true;
        } else {
            this.passed = false;
        }
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
        if(this.points >= quiz.getMax_points() * quiz.getPass_percent()){
            this.passed = true;
        } else {
            this.passed = false;
        }
        if(passed != this.passed){
            throw new RuntimeException("Pass criteria not met");
        }
    }
}
