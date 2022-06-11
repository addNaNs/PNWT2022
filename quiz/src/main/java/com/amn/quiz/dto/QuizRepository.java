package com.amn.quiz.dto;

import com.amn.quiz.models.Quiz;
import org.springframework.data.repository.CrudRepository;


public interface QuizRepository extends CrudRepository<Quiz, Integer> {
}
