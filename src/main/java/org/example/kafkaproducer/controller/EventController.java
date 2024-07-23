package org.example.kafkaproducer.controller;

import org.example.kafkaproducer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> sendMessageToKafkaTopic(@PathVariable String message) {
        try {
            kafkaMessagePublisher.sendMessageToKafkaTopic(message);
            return ResponseEntity.ok("Message sent to Kafka Topic");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error while sending message to Kafka Topic");
        }
    }
}
