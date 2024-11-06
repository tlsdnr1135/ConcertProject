package com.hhp.concertreservation.A_presentation.dto.payment;

import com.hhp.concertreservation.B_application.dto.payment.ConcertPaymentInput;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ConcertReservationPaymentReq {

    private Long userId;
    private String token;
    private Long concertId;
    private Long concertDetailId;
    private Long seatIds;
    private int amount;

    @Builder
    public ConcertReservationPaymentReq(Long userId, String token, Long concertId, Long concertDetailId, Long seatIds, int amount) {
        this.userId = userId;
        this.token = token;
        this.concertId = concertId;
        this.concertDetailId = concertDetailId;
        this.seatIds = seatIds;
        this.amount = amount;
    }

    public ConcertPaymentInput toInput(ConcertReservationPaymentReq req) {
        return ConcertPaymentInput.builder()
                .userId(req.userId)
                .concertId(req.concertId)
                .concertDetailId(req.concertDetailId)
                .seatIds(req.seatIds)
                .amount(req.amount)
                .build();
    }
}