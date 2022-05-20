package com.gimaletdinov.exampleProject.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    //run rabbitmq docker image - docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management
    public final static String QUEUE_NAME = "queue_messages";
    public final static String EXCHANGE_NAME = "exchange_messages";
    public final static String ROUTING_KEY = "message";

    @Bean
    public DirectExchange direct () {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Binding binding(DirectExchange direct, Queue queue) {
        return BindingBuilder.bind(queue)
                .to(direct)
                .with(ROUTING_KEY);
    }

}
