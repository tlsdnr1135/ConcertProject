package com.hhp.concertreservation.B_application.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteTokenInput {

    private String token;
    private Long concertId;

    @Builder
    public DeleteTokenInput(String token, Long concertId) {
        this.token = token;
        this.concertId = concertId;
    }
}
