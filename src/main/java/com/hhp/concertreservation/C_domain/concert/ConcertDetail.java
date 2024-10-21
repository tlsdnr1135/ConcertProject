package com.hhp.concertreservation.C_domain.concert;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "CONCERT_DETAIL")
public class ConcertDetail {

    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CONCERT_ID")
    private Concert concert;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "MAX_SEAT_COUNT")
    private Integer maxSeatCount;

    @Column(name = "PERFORMANCE_DATE")
    private LocalDateTime performanceDate;

    @Column(name = "START_RESERVATION_TIME")
    private LocalDateTime startReservationTime;

    @Column(name = "END_RESERVATION_TIME")
    private LocalDateTime endReservationTime;

}
