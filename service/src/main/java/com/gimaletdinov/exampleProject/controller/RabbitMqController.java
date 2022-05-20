package com.gimaletdinov.exampleProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gimaletdinov.exampleProject.dto.request.MessageRequsetDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.gimaletdinov.exampleProject.config.RabbitMqConfig.QUEUE_NAME;
import static com.gimaletdinov.exampleProject.config.RabbitMqConfig.ROUTING_KEY;

@RestController
@RequestMapping("api/rabbit")
public class RabbitMqController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private ObjectMapper objectMapper;

    private Map<Integer, String> messagesStore = new HashMap<>();
    private Integer messageId = 0;

    @PostMapping
    public ObjectSuccessResponseDto putMessageToQueueAndSendIdOfMessage(@RequestBody MessageRequsetDto messageRequsetDto) {
        messageId++;
        messageRequsetDto.setId(messageId);
        try {
            rabbitTemplate.convertAndSend(directExchange.getName(), ROUTING_KEY, objectMapper.writeValueAsString(messageRequsetDto));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка преобразования сообщения в --> JSON при отправке в очередь Rabbit" + e);
        }

        ObjectSuccessResponseDto objectSuccessResponseDto = new ObjectSuccessResponseDto();
        objectSuccessResponseDto.setResult("Success. Wait your data with id = " + messageRequsetDto.getId());
        return objectSuccessResponseDto;
    }

    @GetMapping("/{id}")
    public ObjectSuccessResponseDto getMessageFromMessagesStoreIfPresent(@PathVariable Integer id){
        ObjectSuccessResponseDto objectSuccessResponseDto = new ObjectSuccessResponseDto();
        if (messagesStore.containsKey(id)){
            objectSuccessResponseDto.setResult(messagesStore.get(id));
        }
        else {
            objectSuccessResponseDto.setResult("Data with id = " + id + " have not proceed yet. Wait.");
        }
        return objectSuccessResponseDto;
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void getMessageFromRabbitQueueAndSendToAnotherQueue(String objectMessage){
        MessageRequsetDto messageFromQueue = null;
        try {
            messageFromQueue = objectMapper.readValue(objectMessage, MessageRequsetDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка преобразования сообщения из JSON ---> при отправке в очередб Rabbit" + e);
        }
        messagesStore.put(messageFromQueue.getId(), messageFromQueue.getMessage());
        System.out.println("This message from rabbit's queue put to messages store: " + objectMessage);
    }
}
