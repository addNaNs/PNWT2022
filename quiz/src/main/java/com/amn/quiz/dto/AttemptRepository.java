package com.amn.quiz.dto;

import com.amn.quiz.models.Attempt;
import org.springframework.data.repository.CrudRepository;

public interface AttemptRepository extends CrudRepository<Attempt, Integer> {
}
