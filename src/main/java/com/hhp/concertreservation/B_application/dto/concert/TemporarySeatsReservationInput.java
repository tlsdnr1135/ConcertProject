package com.hhp.concertreservation.B_application.dto.concert;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TemporarySeatsReservationInput {

    private Long seatId;
    private Long userId;

    @Builder
    public TemporarySeatsReservationInput(Long seatId, Long userId) {
        this.seatId = seatId;
        this.userId = userId;
    }
}
