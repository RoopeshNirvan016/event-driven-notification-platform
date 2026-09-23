package org.notificationplatform.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.notificationplatform.kafka.event.NotificationEvent;
import org.notificationplatform.notification.dto.NotificationCreateRequest;
import org.notificationplatform.notification.dto.NotificationCreateResponse;
import org.notificationplatform.notification.dto.OutBoxEvent;
import org.notificationplatform.notification.entities.NotificationEventLogs;
import org.notificationplatform.notification.enums.NotificationStatus;
import org.notificationplatform.notification.repositories.NotificationEventLogsRepository;
import org.notificationplatform.notification.repositories.OutboxEventRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationEventLogsRepository notificationEventLogsRepository;
    private final ObjectMapper objectMapper;
    private final OutboxEventRepository outboxEventRepository;

    public NotificationServiceImpl(NotificationEventLogsRepository notificationEventLogsRepository,  ObjectMapper objectMapper, OutboxEventRepository outboxEventRepository) {
        this.notificationEventLogsRepository = notificationEventLogsRepository;
        this.objectMapper = objectMapper;
        this.outboxEventRepository = outboxEventRepository;
    }

    @Transactional
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
        OutBoxEvent outBoxEvent = new OutBoxEvent();
        outBoxEvent.setEventType(notificationCreateRequest.getEventType());
        outBoxEvent.setAggregateId(nL.getId());
        outBoxEvent.setAggregateType("NOTIFICATION");
        try {
            outBoxEvent.setPayload(objectMapper.writeValueAsString(notificationEvent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        outBoxEvent.setCreatedAt(Instant.now());
        outBoxEvent.setPublished(false);

        outboxEventRepository.save(outBoxEvent);
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
