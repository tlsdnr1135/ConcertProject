package com.hhp.concertreservation.B_application.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReissueTokenInput {

    private Long concertId;
    private Long userId;

    @Builder
    public ReissueTokenInput(Long concertId, Long userId) {
        this.concertId = concertId;
        this.userId = userId;
    }

}
