package com.example.blog_app.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic notificationMail() { 
        return TopicBuilder.name("notificationMail").build();
    }
    @Bean
    public NewTopic commentMail() { 
        return TopicBuilder.name("commentBuilder").build();
    }

    
}
