package org.notificationplatform.notification.service;

import org.notificationplatform.notification.dto.NotificationCreateRequest;
import org.notificationplatform.notification.dto.NotificationCreateResponse;


public interface NotificationService {

      public NotificationCreateResponse pushNotification(NotificationCreateRequest notificationCreateRequest);
}
