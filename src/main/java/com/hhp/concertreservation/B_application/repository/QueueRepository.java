package com.hhp.concertreservation.B_application.repository;

import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;

import java.util.UUID;

public interface QueueRepository {

    void save(Queue queue); // 저장
    void save(QueueItem queueItem); // 저장

    Queue findQueueByConcertId(Long concertId); //대기열 조회

    int getQueueActiveUserCount(Long ConcertId);
    int getQueueItemWaitingProcedure(UUID userId, Long queueId);

}
