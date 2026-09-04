package org.notificationplatform.notification.repositories;

import org.notificationplatform.notification.entities.NotificationEventLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationEventLogsRepository extends JpaRepository<NotificationEventLogs, Long> {

}
