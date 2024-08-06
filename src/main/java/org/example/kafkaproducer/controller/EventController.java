package org.example.kafkaproducer.controller;

import org.example.dto.Customer;
import org.example.kafkaproducer.service.KafkaMessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> sendMessageToKafkaTopic(@PathVariable String message) {
        try {
            kafkaMessagePublisher.sendMessageToKafkaTopic(message);
            logger.info("Message sent to Kafka Topic : {}", message);
            return ResponseEntity.ok("Message sent to Kafka Topic");
        } catch (Exception e) {
            logger.error("Error while sending message to Kafka Topic : {}", e.getMessage());
            return ResponseEntity.status(500).body("Error while sending message to Kafka Topic");
        }
    }

    @PostMapping("/publish")
    public void sendEventsToKafkaTopic(@RequestBody Customer customer) {
        logger.info("Customer Controller sent to Kafka Topic : {}", customer.toString());
        kafkaMessagePublisher.sendEventsToKafkaTopic(customer);
    }
}
