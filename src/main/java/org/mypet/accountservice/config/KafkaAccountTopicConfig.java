package org.mypet.accountservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaAccountTopicConfig {

    @Value("$spring.kafka.topic_name")
    private String topicName;

    @Bean
    public NewTopic accountTopic() {
        return TopicBuilder
                .name(topicName)
                .build();
    }
}
