package com.hhp.concertreservation.C_domain.concert;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "CONCERT_DETAIL")
public class ConcertDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONCERT_ID")
    private Long concertId;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "MAX_SEAT_COUNT")
    private Integer maxSeatCount;

    @Column(name = "PERFORMANCE_DATE")
    private LocalDateTime performanceDate;

    @Builder
    public ConcertDetail(Long id, Long concertId, String place, Integer price, Integer maxSeatCount, LocalDateTime performanceDate) {
        this.id = id;
        this.concertId = concertId;
        this.place = place;
        this.price = price;
        this.maxSeatCount = maxSeatCount;
        this.performanceDate = performanceDate;
    }
}
