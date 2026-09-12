package org.notificationplatform.notification.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.notificationplatform.notification.enums.Business;
import org.notificationplatform.notification.enums.Channel;
import org.notificationplatform.notification.enums.NotificationStatus;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "notification_event_logs")
@NoArgsConstructor
@Getter
@Setter
public class NotificationEventLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "userId",nullable = false)
    private Integer userId;

    @Column(name = "email", nullable = false)
    private String email;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "channels", columnDefinition = "jsonb", nullable = false)
    private List<Channel> channels;

    @Column(name= "created_time", columnDefinition = "TIMESTAMP WITH TIME ZONE" )
    private Instant createdTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "Text" )
    private NotificationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "business_type", columnDefinition = "Text")
    private Business businessType;



}
