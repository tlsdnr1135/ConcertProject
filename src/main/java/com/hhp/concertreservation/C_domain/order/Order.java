package com.hhp.concertreservation.C_domain.order;

import com.hhp.concertreservation.C_domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CONCERT_ID")
    private Long concertId;

    @Column(name = "CONCERT_DETAIL_ID")
    private Long concertDetailId;

    @Column(name = "SEAT_ID")
    private Long seatId;

    @Column(name = "AMOUBT")
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus status;

    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @Builder
    public Order(Long id, Long userId, Long concertId, Long concertDetailId, Long seatId, int amount, OrderStatus status, LocalDateTime createdTime) {
        this.id = id;
        this.userId = userId;
        this.concertId = concertId;
        this.concertDetailId = concertDetailId;
        this.seatId = seatId;
        this.amount = amount;
        this.status = status;
        this.createdTime = createdTime;
    }
}
