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
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank
    private String eventType;

    @NotBlank(message = "Message is required")
    private String message;

    @NotEmpty(message = "List of Channels are required")
    private List<Channel> channels;

    @NotNull(message = "Business type is required")
    private Business businessType;
}
