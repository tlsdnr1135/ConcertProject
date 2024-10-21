package com.hhp.concertreservation.D_infrastructure.repositoryImpl;

import com.hhp.concertreservation.B_application.repository.QueueRepository;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import com.hhp.concertreservation.D_infrastructure.jpa.QueueItemJpaRepository;
import com.hhp.concertreservation.D_infrastructure.jpa.QueueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class QueueRepositoryImpl implements QueueRepository {

    private final QueueJpaRepository queueJpaRepository;
    private final QueueItemJpaRepository queueItemJpaRepository;

    @Override
    public void save(Queue queue) {
        queueJpaRepository.save(queue);
    }

    @Override
    public void save(QueueItem queueItem) {
        queueItemJpaRepository.save(queueItem);
    }

    @Override
    public Queue findQueueByConcertId(Long concertId) {
        Queue queue = queueJpaRepository.findQueueByConcertId(concertId);
        return queue;
    }

    @Override
    public int getQueueActiveUserCount(Long concertId) {
        return queueItemJpaRepository.getActiveUserCount(concertId);
    }

    @Override
    public int getQueueItemWaitingProcedure(UUID tokenId, Long queueId) {
        return queueItemJpaRepository.getQueueItemWaitingProcedure(tokenId, queueId);
    }
}
