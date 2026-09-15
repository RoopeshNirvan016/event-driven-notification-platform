package org.notificationplatform.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.notificationplatform.kafka.event.NotificationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventProducer {

    private static final String TOPIC = "notification-events";

    private final KafkaTemplate<String, NotificationEvent> notificationEventKafkaTemplate;

    public void publish(NotificationEvent notificationEvent){
        notificationEventKafkaTemplate.send(TOPIC,notificationEvent);
    }

}
