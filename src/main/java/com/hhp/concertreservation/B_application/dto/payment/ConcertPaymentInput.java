package com.hhp.concertreservation.B_application.dto.payment;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ConcertPaymentInput {

    private Long userId;
    private String token;
    private Long concertId;
    private Long concertDetailId;
    private Long seatIds;
    private int amount;

    @Builder
    public ConcertPaymentInput(Long userId, String token, Long concertId, Long concertDetailId, Long seatIds, int amount) {
        this.userId = userId;
        this.token = token;
        this.concertId = concertId;
        this.concertDetailId = concertDetailId;
        this.seatIds = seatIds;
        this.amount = amount;
    }
}