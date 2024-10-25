package com.hhp.concertreservation.B_application.dto.concert;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ConcertAvailableSeatsInput {

    private Long concertDetailId;

    @Builder
    public ConcertAvailableSeatsInput(Long concertDetailId) {
        this.concertDetailId = concertDetailId;
    }

}
