package com.hhp.concertreservation.D_infrastructure.repositoryImpl.queue;

import com.hhp.concertreservation.B_application.repository.queue.QueueItemRepository;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import com.hhp.concertreservation.D_infrastructure.jpa.queue.QueueItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
public class QueueItemRepositoryImpl implements QueueItemRepository {

    private final QueueItemJpaRepository queueItemJpaRepository;

    @Override
    public void save(QueueItem queueItem) {
        queueItemJpaRepository.save(queueItem);
    }

    @Override
    public int getQueueItemWaitingProcedure(String token, Long queueId) {
        return queueItemJpaRepository.getQueueItemWaitingProcedure(token, queueId);
    }

    @Override
    public boolean existsQueueItemByTokenAndQueueId(String token, Long queueId) {
        return queueItemJpaRepository.existsByTokenAndQueueId(token, queueId);
    }

    @Override
    public Optional<QueueItem> findQueueItemByTokenAndQueueId(String token, Long queueId) {
        Optional<QueueItem> queueItem = queueItemJpaRepository.findByTokenAndQueueId(token, queueId);
        return queueItem;
    }

    @Override
    public void deleteQueueItemById(Long queueItemId) {
        queueItemJpaRepository.deleteById(queueItemId);
    }

    @Override
    public int getQueueActiveUserCount(Long concertId) {
        return queueItemJpaRepository.getActiveUserCount(concertId);
    }
}
