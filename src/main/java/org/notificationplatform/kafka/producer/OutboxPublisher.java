package org.notificationplatform.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.notificationplatform.notification.dto.OutBoxEvent;
import org.notificationplatform.notification.repositories.OutboxEventRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxPublisher {
    private static final String TOPIC = "notification-events";

    private final OutboxEventRepository outboxEventRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void publishEvents() {

        var events =
                outboxEventRepository
                        .findTop100ByPublishedFalseOrderByCreatedAtAsc();

        for (OutBoxEvent event : events) {

            kafkaTemplate.send(
                    TOPIC,
                    event.getAggregateId().toString(),
                    event.getPayload()
            ).whenComplete((result,exception)-> {
                if(exception == null){
                    event.setPublished(true);
                    outboxEventRepository.save(event);
                    System.out.println( "Published Outbox Event: " + event.getId());
                }else{
                    System.err.println(
                            "Failed to publish Outbox Event: "
                                    + event.getId()
                                    + " Error: "
                                    + exception.getMessage()
                    );
                }
            });


        }
    }
}
