package org.notificationplatform.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    private Integer userId;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String eventType;

    @NotBlank
    private String message;

    @NotEmpty
    private List<Channel> channels;

    @NotNull
    private Business businessChannel;
}
