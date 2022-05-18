package com.gimaletdinov.exampleProject.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitListener(queues = "queue1")
    public void getMessageFromRabbitQueueAndSendToAnotherQueue(String message){
        System.out.println("This message from rabbit's queue: " + message);
    }
}
