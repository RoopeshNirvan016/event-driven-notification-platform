package org.notificationplatform.notification.repositories;

import org.notificationplatform.notification.dto.OutBoxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxEventRepository extends JpaRepository<OutBoxEvent,Integer> {

    List<OutBoxEvent> findTop100ByPublishedFalseOrderByCreatedAtAsc();
}
