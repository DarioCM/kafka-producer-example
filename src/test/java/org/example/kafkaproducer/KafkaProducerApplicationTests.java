package org.example.kafkaproducer;

import org.example.dto.Customer;
import org.example.kafkaproducer.service.KafkaMessagePublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaProducerApplicationTests {

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @DynamicPropertySource
    public static void initKafkaProducer(DynamicPropertyRegistry registry) {
       registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);

    }

    @Test
    public void testSendEventsToKafkaTopic() {
        kafkaMessagePublisher.sendEventsToKafkaTopic(new Customer(1, "John", "sdsad@dfdf.com", "1212112"));
        await().atMost(Duration.ofSeconds(10)).untilAsserted(() -> {
            //assertThat(kafkaMessagePublisher.getMessages().size()).isEqualTo(1);
        });
    }

}
