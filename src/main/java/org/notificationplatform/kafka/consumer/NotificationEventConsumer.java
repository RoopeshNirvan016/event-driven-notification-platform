package org.notificationplatform.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventConsumer {

    @KafkaListener(topics = "notification-events",groupId = "notification-workers")
    public void consume(String message) {
        System.out.println( "Received notification event: " + message);
    }
}
