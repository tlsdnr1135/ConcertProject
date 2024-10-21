package com.hhp.concertreservation.D_infrastructure.jpa;

import com.hhp.concertreservation.C_domain.queue.QueueItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface QueueItemJpaRepository extends JpaRepository<QueueItem, Long> {

    @Query("SELECT COUNT(1) FROM QueueItem A " +
            "JOIN A.queue q WHERE A.status = 'ACTIVE'")
    int getActiveUserCount(@Param("concertId") Long concertId);

    @Query("SELECT COUNT(1) FROM QueueItem A " +
            "WHERE A.status = 'ACTIVE' " +
            "AND A.queue = :queueId " +
            "AND A.createdAt <= (SELECT B.createdAt FROM QueueItem B" +
            "                   WHERE B.token = :tokenId " +
            "                   AND B.queue = :queueId) " +
            "ORDER BY A.createdAt DESC")
    int getQueueItemWaitingUserCount(@Param("tokenId") UUID userId, @Param("queueId") Long queueId);

}
