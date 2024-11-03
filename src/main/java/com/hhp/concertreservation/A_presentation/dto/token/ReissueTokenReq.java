package com.hhp.concertreservation.A_presentation.dto.token;

import lombok.Getter;

@Getter
public class ReissueTokenReq {

    private Long concertId;
    private Long userId;

}
