package org.notificationplatform.notification.service;

import org.notificationplatform.notification.dto.NotificationCreateRequest;
import org.notificationplatform.notification.dto.NotificationCreateResponse;
import org.notificationplatform.notification.entities.NotificationEventLogs;
import org.notificationplatform.notification.enums.NotificationStatus;
import org.notificationplatform.notification.repositories.NotificationEventLogsRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationEventLogsRepository notificationEventLogsRepository;

    public NotificationServiceImpl(NotificationEventLogsRepository notificationEventLogsRepository) {
        this.notificationEventLogsRepository = notificationEventLogsRepository;
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
        return notificationCreateResponse;
    }
}
