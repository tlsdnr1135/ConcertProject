package com.hhp.concertreservation.C_domain.order;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CONCERT_ID")
    private Long concertId;

    @Column(name = "CONCERT_DETAIL_ID")
    private Long concertDetailId;

    @Column(name = "SEAT_ID")
    private Long seatId;

    @Column(name = "AMOUBT")
    private int amount;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

}
