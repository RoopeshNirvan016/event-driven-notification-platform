package org.notificationplatform.notification.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.notificationplatform.notification.enums.Business;
import org.notificationplatform.notification.enums.Channel;

import java.util.List;

@Getter
@Setter
public class NotificationCreateRequest {

    @NotNull
    Integer userId;

    @NotNull
    String email;

    @NotNull
    String eventType;

    @NotNull
    String message;

    @NotNull
    List<Channel> channels;

    @NotNull
    Business businessChannel;
}
