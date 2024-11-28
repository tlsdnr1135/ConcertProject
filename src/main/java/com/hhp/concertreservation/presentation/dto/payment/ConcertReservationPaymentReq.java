package com.hhp.concertreservation.presentation.dto.payment;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ConcertReservationPaymentReq {

    private String userId;
    private Long concertId;
    private LocalDateTime reservationDate;
    private List<Long> seatIds;
    private int amount;
}
