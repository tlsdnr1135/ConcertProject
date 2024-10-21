package com.hhp.concertreservation.C_domain.queue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "QUEUE")
public class Queue {

    @Id
    @Column(name = "QUEUE_ID")
    private Long id;

    @Column(name = "CONCERT_ID")
    private Long concertId;

    @Column(name = "MAX_ACTIVE_USER")
    private int maxActiveUser;


}