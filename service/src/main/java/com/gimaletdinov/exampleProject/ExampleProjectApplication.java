package com.gimaletdinov.exampleProject;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExampleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleProjectApplication.class, args);
    }

    @Bean
    public Queue queue1() {
        return new Queue("queue1");
    }
}
