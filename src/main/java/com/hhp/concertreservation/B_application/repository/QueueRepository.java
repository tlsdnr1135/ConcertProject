package com.hhp.concertreservation.B_application.repository;

import com.hhp.concertreservation.C_domain.queue.Queue;
import com.hhp.concertreservation.C_domain.queue.QueueItem;

import java.util.UUID;

public interface QueueRepository {

    void save(Queue queue); // 저장
    void save(QueueItem queueItem); // 저장

    Queue findQueueByConcertId(Long concertId); //대기열 조회

    int getQueueActiveUserCount(Long ConcertId);
    int getQueueItemWaitingUserCount(UUID userId, Long queueId);

}
