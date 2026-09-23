package org.notificationplatform.notification.controller;

import jakarta.validation.Valid;
import org.notificationplatform.notification.dto.NotificationCreateRequest;
import org.notificationplatform.notification.dto.NotificationCreateResponse;
import org.notificationplatform.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/api/v1/notifications")
    public ResponseEntity<NotificationCreateResponse> pushNotification(@Valid @RequestBody NotificationCreateRequest notificationCreateRequest){
        NotificationCreateResponse notificationCreateResponse = notificationService.pushNotification(notificationCreateRequest);
        return ResponseEntity.accepted().body(notificationCreateResponse);
    }
}
