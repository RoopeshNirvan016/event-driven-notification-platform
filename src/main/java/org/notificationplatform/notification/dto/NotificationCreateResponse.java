package org.notificationplatform.notification.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.notificationplatform.notification.enums.NotificationStatus;

@Getter
public class NotificationCreateResponse {

    private NotificationStatus status;

    private Long notificationId;

    private NotificationCreateResponse(NotificationResponseBuilder notificationResponseBuilder) {
        this.status = notificationResponseBuilder.status;
        this.notificationId = notificationResponseBuilder.notificationId;
    }

    public static NotificationResponseBuilder getBuilder(){
        return new NotificationResponseBuilder();
    }


    public static class NotificationResponseBuilder {

        private NotificationStatus status;


        private Long notificationId;


        public NotificationResponseBuilder status(NotificationStatus status) {
            this.status = status;
            return this;
        }

        public NotificationResponseBuilder notificationId(Long notificationId) {
            this.notificationId = notificationId;
            return this;
        }

        public NotificationCreateResponse build(){
            return new NotificationCreateResponse(this);
        }
    }
}
