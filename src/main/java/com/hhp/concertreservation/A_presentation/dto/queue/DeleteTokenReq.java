package com.hhp.concertreservation.A_presentation.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeleteTokenReq {

    private String token;
    private Long concertId;

}
