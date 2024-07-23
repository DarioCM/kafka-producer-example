package org.example.kafkaproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object>  kafkaTemplate;

    public void sendMessageToKafkaTopic(String message) {
        CompletableFuture<SendResult<String, Object>> send = kafkaTemplate.send("java-testTopic-1", message);
        send.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Error while sending message to Kafka Topic");
            } else {
                System.out.println("Message sent to Kafka Topic");
            }
        });
    }

}
