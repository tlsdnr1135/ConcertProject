package com.hhp.concertreservation.A_presentation.dto.queue;

import lombok.Getter;

@Getter
public class IssueTokenReq {

    private Long concertId;
    private Long userId;

}
