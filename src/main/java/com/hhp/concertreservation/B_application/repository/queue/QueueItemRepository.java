package com.hhp.concertreservation.B_application.repository.queue;

import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueueItemRepository {

    void save(QueueItem queueItem); // 저장

    int getQueueActiveUserCount(Long ConcertId);
    int getQueueItemWaitingProcedure(String token, Long queueId);

    boolean existsQueueItemByTokenAndQueueId(String token, Long queueId);
    Optional<QueueItem> findQueueItemByTokenAndQueueId(String token, Long queueId);

    void deleteQueueItemById(Long queueItemId);

}
