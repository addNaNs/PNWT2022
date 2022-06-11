package com.amn.quiz.util.consumer;

import com.amn.quiz.dto.QuizRepository;
import com.amn.quiz.models.Quiz;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuizConsumer {

    @Autowired
    private QuizRepository quizRepository;

    @RabbitListener(queues = "quiz-queue")
    public void consumeMessageFromQueue(QuizMessage courseMessage){
        System.out.println("Message received from queue "+courseMessage.getCourse_id());
        List<Integer> forDelete = new ArrayList<>();
        quizRepository.findAll().forEach(el ->
        {
            if(el.getCourse_id().equals(courseMessage.getCourse_id()) ) {
                forDelete.add(el.getId());
            }
        });
        forDelete.forEach(el -> quizRepository.deleteById(el));
    }
}