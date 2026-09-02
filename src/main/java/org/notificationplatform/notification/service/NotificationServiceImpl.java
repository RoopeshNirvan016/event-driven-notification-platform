package org.notificationplatform.notification.service;

import jakarta.persistence.EntityManager;
import org.notificationplatform.notification.dto.NotificationCreateRequest;
import org.notificationplatform.notification.dto.NotificationCreateResponse;
import org.notificationplatform.notification.entities.NotificationEventLogs;
import org.notificationplatform.notification.enums.NotificationStatus;
import org.notificationplatform.notification.repositories.NotificationEventLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationEventLogsRepository notificationEventLogsRepository;

    @Autowired
    EntityManager entityManager;

    @Override
    public NotificationCreateResponse pushNotification(NotificationCreateRequest notificationCreateRequest) {
        //Need to create an event log for future reference
        NotificationEventLogs notificationEventLogs = new NotificationEventLogs();
        notificationEventLogs.setEmail(notificationCreateRequest.getEmail());
        notificationEventLogs.setCreatedTime(Instant.now());
        notificationEventLogs.setBusinessType(notificationCreateRequest.getBusinessChannel());
        notificationEventLogs.setChannelList(notificationCreateRequest.getChannels());
        NotificationEventLogs nL = notificationEventLogsRepository.save(notificationEventLogs);

        NotificationCreateResponse notificationCreateResponse = NotificationCreateResponse.getBuilder().notificationId(nL.getId()).status(NotificationStatus.QUEUED).build();
        return notificationCreateResponse;
    }
}
