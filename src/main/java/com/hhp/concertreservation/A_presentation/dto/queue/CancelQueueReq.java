package com.hhp.concertreservation.A_presentation.dto.queue;

import lombok.Getter;

@Getter
public class CancelQueueReq {

    private String token;
    private Long concertId;

}
