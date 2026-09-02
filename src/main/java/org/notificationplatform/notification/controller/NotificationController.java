package org.notificationplatform.notification.controller;

import org.notificationplatform.notification.dto.NotificationCreateRequest;
import org.notificationplatform.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    public ResponseEntity<?> pushNotification(NotificationCreateRequest notificationCreateRequest){
       return ResponseEntity.ok(notificationService.pushNotification(notificationCreateRequest));
    }
}
