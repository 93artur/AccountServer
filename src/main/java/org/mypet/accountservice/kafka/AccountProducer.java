package org.mypet.accountservice.kafka;

import lombok.RequiredArgsConstructor;
import org.mypet.accountservice.dto.TransferAccountDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class AccountProducer {

    @Value("${spring.kafka.topics.test-topic}")
    private String topicName;

    private final KafkaTemplate<Object, TransferAccountDto> kafkaTemplate;

    public void sendMessage(TransferAccountDto transferAccountDto) {
        Message<TransferAccountDto> message = MessageBuilder
                .withPayload(transferAccountDto)
                .setHeader(TOPIC, topicName)
                .build();

        kafkaTemplate.send(message);
    }
}
