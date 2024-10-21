package com.hhp.concertreservation.A_presentation.dto.payment;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ConcertReservationPaymentRes {

    private String userId;
    private Long concertId;
    private LocalDateTime reservationDate;
    private List<Integer> seatIds;
    private int usePoint;
    private int point;

    @Builder
    public ConcertReservationPaymentRes(String userId, Long concertId, LocalDateTime reservationDate, List<Integer> seatIds, int usePoint, int point) {
        this.userId = userId;
        this.concertId = concertId;
        this.reservationDate = reservationDate;
        this.seatIds = seatIds;
        this.usePoint = usePoint;
        this.point = point;
    }
}
