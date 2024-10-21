package com.hhp.concertreservation.D_infrastructure.jpa;

import com.hhp.concertreservation.C_domain.queue.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QueueJpaRepository extends JpaRepository<Queue, Long> {



    Queue findQueueByConcertId(Long concertId);
}
