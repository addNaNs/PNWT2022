package com.amn.courses.util.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CourseConsumer {

    @RabbitListener(queues = "course-queue")
    public void consumeMessageFromQueue(CourseMessage courseMessage){
        System.out.println("Message received from queue "+courseMessage.getCourse_id().toString());
    }
}