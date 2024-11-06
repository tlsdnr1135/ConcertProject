package com.hhp.concertreservation.B_application.dto.token;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CancelQueueInput {

    private String token;
    private Long concertId;

    @Builder
    public CancelQueueInput(String token, Long concertId) {
        this.token = token;
        this.concertId = concertId;
    }
}
