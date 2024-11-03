package com.hhp.concertreservation.B_application.dto.token;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReissueTokenOutput {

    private String token;

    @Builder
    public ReissueTokenOutput(String token) {
        this.token = token;
    }
}
