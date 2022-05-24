package com.gimaletdinov.exampleProject.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String KAFKA_TOPIC_NAME = "kafkaTopic";
    public static final String KAFKA_GROUP_ID = "foo";
    @Bean
    public NewTopic kafkaTopicBean(){
        return TopicBuilder.name(KAFKA_TOPIC_NAME).build();
    }
}
