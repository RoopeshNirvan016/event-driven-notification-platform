package org.notificationplatform.kafka.event;

import lombok.Getter;
import lombok.Setter;
import org.notificationplatform.notification.enums.Channel;

import java.util.List;

@Getter
@Setter
public class NotificationEvent {
    private Long notificationId;
    private Integer userId;
    private String email;
    private String eventType;
    private String message;
    private List<Channel> channels;
}
