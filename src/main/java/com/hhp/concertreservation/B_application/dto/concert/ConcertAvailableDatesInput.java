package com.hhp.concertreservation.B_application.dto.concert;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ConcertAvailableDatesInput {

    private Long concertId;

    @Builder
    public ConcertAvailableDatesInput(Long concertId) {
        this.concertId = concertId;
    }
}
