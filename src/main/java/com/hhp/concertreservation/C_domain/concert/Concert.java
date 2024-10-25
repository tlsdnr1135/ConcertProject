package com.hhp.concertreservation.C_domain.concert;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "CONCERT")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SINGER_ID")
    private Long singerId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "START_RESERVATION_TIME")
    private LocalDateTime startReservationTime;

    @Column(name = "END_RESERVATION_TIME")
    private LocalDateTime endReservationTime;

    @Builder
    public Concert(Long id, Long singerId, String title, LocalDateTime startReservationTime, LocalDateTime endReservationTime) {
        this.id = id;
        this.singerId = singerId;
        this.title = title;
        this.startReservationTime = startReservationTime;
        this.endReservationTime = endReservationTime;
    }
}
