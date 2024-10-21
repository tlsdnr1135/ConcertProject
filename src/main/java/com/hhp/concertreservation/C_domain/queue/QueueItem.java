package com.hhp.concertreservation.C_domain.queue;

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

    @ManyToOne
    @JoinColumn(name = "QUEUE_ID")
    private Queue queue;

    @OneToOne
    @JoinColumn(name = "TOKEN_ID")
    private Token token;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private QueueStatus status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Builder
    public QueueItem(Long id, Queue queue, Token token, QueueStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.queue = queue;
        this.token = token;
        this.status = status;
        this.createdAt = createdAt;
    }
}
