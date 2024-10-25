package com.hhp.concertreservation.D_infrastructure.repositoryImpl.queue;

import com.hhp.concertreservation.B_application.repository.queue.QueueRepository;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import com.hhp.concertreservation.D_infrastructure.jpa.queue.QueueItemJpaRepository;
import com.hhp.concertreservation.D_infrastructure.jpa.queue.QueueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class QueueRepositoryImpl implements QueueRepository {

    private final QueueJpaRepository queueJpaRepository;

    @Override
    public void save(Queue queue) {
        queueJpaRepository.save(queue);
    }

    @Override
    public Optional<Queue> findQueueByConcertId(Long concertId) {
        Optional<Queue> queue = queueJpaRepository.findQueueByConcertId(concertId);
        return queue;
    }




}
