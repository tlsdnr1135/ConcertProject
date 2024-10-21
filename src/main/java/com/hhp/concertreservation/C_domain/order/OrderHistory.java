package com.hhp.concertreservation.C_domain.order;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "ORDER_HISTORY")
public class OrderHistory {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "CONCERT_DETAIL_ID")
    private Long concertDetailId;

    @Column(name = "SEAT_ID")
    private Long seatId;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REASON")
    private String reason;

}
