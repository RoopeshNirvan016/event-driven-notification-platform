package org.notificationplatform.notification.service;

import org.notificationplatform.kafka.event.NotificationEvent;
import org.notificationplatform.kafka.producer.NotificationEventProducer;
import org.notificationplatform.notification.dto.NotificationCreateRequest;
import org.notificationplatform.notification.dto.NotificationCreateResponse;
import org.notificationplatform.notification.entities.NotificationEventLogs;
import org.notificationplatform.notification.enums.NotificationStatus;
import org.notificationplatform.notification.repositories.NotificationEventLogsRepository;
import org.springframework.kafka.event.KafkaEvent;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationEventLogsRepository notificationEventLogsRepository;
    private final NotificationEventProducer notificationEventProducer;

    public NotificationServiceImpl(NotificationEventLogsRepository notificationEventLogsRepository, NotificationEventProducer notificationEventProducer) {
        this.notificationEventLogsRepository = notificationEventLogsRepository;
        this.notificationEventProducer = notificationEventProducer;
    }

    @Override
    public NotificationCreateResponse pushNotification(NotificationCreateRequest notificationCreateRequest) {
        //Need to create an event log for future reference
        NotificationEventLogs notificationEventLogs = new NotificationEventLogs();
        notificationEventLogs.setEmail(notificationCreateRequest.getEmail());
        notificationEventLogs.setCreatedTime(Instant.now());
        notificationEventLogs.setBusinessType(notificationCreateRequest.getBusinessType());
        notificationEventLogs.setChannels(notificationCreateRequest.getChannels());
        notificationEventLogs.setUserId(notificationCreateRequest.getUserId());
        notificationEventLogs.setStatus(NotificationStatus.QUEUED);
        NotificationEventLogs nL = notificationEventLogsRepository.save(notificationEventLogs);

        NotificationCreateResponse notificationCreateResponse = NotificationCreateResponse.getBuilder().notificationId(nL.getId()).status(NotificationStatus.QUEUED).build();

        NotificationEvent notificationEvent = buildNotificationEvent(notificationCreateRequest, nL.getId());
        notificationEventProducer.publish(notificationEvent);
        return notificationCreateResponse;
    }

    private NotificationEvent buildNotificationEvent(
            NotificationCreateRequest request,
            Long notificationId) {

        NotificationEvent event = new NotificationEvent();

        event.setNotificationId(notificationId);
        event.setUserId(request.getUserId());
        event.setEmail(request.getEmail());
        event.setEventType(request.getEventType());
        event.setMessage(request.getMessage());
        event.setChannels(request.getChannels());

        return event;
    }
}
