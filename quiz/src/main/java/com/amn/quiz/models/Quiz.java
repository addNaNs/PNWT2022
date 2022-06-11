package com.amn.quiz.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private Integer course_id;
    private Integer max_points;
    private Double pass_percent;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private Set<Score> scores;

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

    public Integer getMax_points() {
        return max_points;
    }

    public void setMax_points(Integer max_points) {
        this.max_points = max_points;
    }

    public Double getPass_percent() {
        return pass_percent;
    }

    public void setPass_percent(Double pass_percent) {
        this.pass_percent = pass_percent;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }
}
