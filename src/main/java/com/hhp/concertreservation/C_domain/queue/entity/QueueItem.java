package com.hhp.concertreservation.C_domain.queue.entity;

import com.hhp.concertreservation.C_domain.enums.QueueStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "QUEUE_ITEM")
public class QueueItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUEUE_ID")
    private Long queueId;

    @Column(name = "TOKEN")
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private QueueStatus status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Builder
    public QueueItem(Long id, Long queueId, String token, QueueStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.queueId = queueId;
        this.token = token;
        this.status = status;
        this.createdAt = createdAt;
    }

    public void changeStatus(QueueStatus status) {
        this.status = status;
    }
}
