package com.hhp.concertreservation.B_application.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemainingTimeInQueueInput {

    String token;
    Long concertId;

    @Builder
    public RemainingTimeInQueueInput(String token, Long concertId) {
        this.token = token;
        this.concertId = concertId;
    }
}
