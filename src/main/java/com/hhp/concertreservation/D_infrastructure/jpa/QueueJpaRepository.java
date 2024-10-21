package com.hhp.concertreservation.D_infrastructure.jpa;

import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueJpaRepository extends JpaRepository<Queue, Long> {



    Queue findQueueByConcertId(Long concertId);
}
