package com.batbudget.reportservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventConsumer {
    @KafkaListener(topics = "transaction-events", groupId = "report-service-group")
    public void listen(ConsumerRecord<String, String> record) {
        // TODO: Deserialize event and implement report logic
        System.out.println("Received transaction event: " + record.value());
    }
}

