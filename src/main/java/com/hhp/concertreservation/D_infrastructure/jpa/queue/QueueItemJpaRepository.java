package com.hhp.concertreservation.D_infrastructure.jpa.queue;

import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QueueItemJpaRepository extends JpaRepository<QueueItem, Long> {

    @Query("SELECT COUNT(1) FROM QueueItem A " +
            "JOIN Queue B ON A.id = B.id " +
            "WHERE A.status = 'ACTIVE' " +
            "AND B.concertId = :concertId")
    int getActiveUserCount(@Param("concertId") Long concertId);

    @Query("SELECT COUNT(1) FROM QueueItem A " +
            "WHERE A.status = 'WAITING' " +
            "AND A.queueId = :queueId " +
            "AND A.createdAt <= (SELECT B.createdAt FROM QueueItem B" +
            "                   WHERE B.token = :token " +
            "                   AND B.queueId = :queueId) " +
            "ORDER BY A.createdAt DESC")
    int getQueueItemWaitingProcedure(@Param("token") String token, @Param("queueId") Long queueId);

    boolean existsByTokenAndQueueId(String token, Long queueId);
    Optional<QueueItem> findByTokenAndQueueId(String token, Long queueId);
}
