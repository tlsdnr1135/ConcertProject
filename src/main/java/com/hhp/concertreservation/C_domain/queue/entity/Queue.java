package com.hhp.concertreservation.C_domain.queue.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "QUEUE")
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUEUE_ID")
    private Long id;

    @Column(name = "CONCERT_ID")
    private Long concertId;

    @Column(name = "MAX_ACTIVE_USER")
    private int maxActiveUser;

    @Builder
    public Queue(Long id, Long concertId, int maxActiveUser) {
        this.id = id;
        this.concertId = concertId;
        this.maxActiveUser = maxActiveUser;
    }

    public boolean isQueueMaxActiveUser(int activeUserCount) {
        if(this.maxActiveUser <= activeUserCount) {
            return true;
        }
        return false;
    }

}