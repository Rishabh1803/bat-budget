package com.rk.batbudget.userservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserEventConsumer {

    @KafkaListener(topics = "user-registered", groupId = "user-service")
    public void consume(String message) {
        log.info("Received user registration event: {}", message);
    }
}
