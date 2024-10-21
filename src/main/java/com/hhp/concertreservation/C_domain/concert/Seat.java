package com.hhp.concertreservation.C_domain.concert;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "SEAT")
public class Seat {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONCERT_DETAIL_ID")
    private Long concertDetailId;

    @Column(name = "SEAT_USER_ID")
    private String seatUserId;

    @Column(name = "SEAT_NUMBER")
    private String seatNumber;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "EXPIRED_TIME")
    private LocalDateTime expiredTime;

}
