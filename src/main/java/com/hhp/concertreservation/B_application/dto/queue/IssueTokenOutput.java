package com.hhp.concertreservation.B_application.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IssueTokenOutput {

    private String token;

    @Builder
    public IssueTokenOutput(String token) {
        this.token = token;
    }
}
