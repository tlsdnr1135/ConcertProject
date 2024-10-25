package com.hhp.concertreservation.D_infrastructure.jpa.queue;

import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QueueJpaRepository extends JpaRepository<Queue, Long> {

    Optional<Queue> findQueueByConcertId(Long concertId);

}
