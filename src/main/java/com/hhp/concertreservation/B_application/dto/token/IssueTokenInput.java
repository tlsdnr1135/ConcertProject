package com.hhp.concertreservation.B_application.dto.token;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IssueTokenInput {

    private Long concertId;
    private Long userId;

    @Builder
    public IssueTokenInput(Long concertId, Long userId) {
        this.concertId = concertId;
        this.userId = userId;
    }
}