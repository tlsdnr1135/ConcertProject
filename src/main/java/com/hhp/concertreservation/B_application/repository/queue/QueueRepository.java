package com.hhp.concertreservation.B_application.repository.queue;

import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;

import java.util.Optional;

public interface QueueRepository {

    void save(Queue queue); // 저장

    Optional<Queue> findQueueByConcertId(Long concertId); //대기열 조회


}
