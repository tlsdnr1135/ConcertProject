package com.hhp.concertreservation.B_application.dto.point;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChargePointsInput {

    private Long userId;
    private int chargePoint;

    @Builder
    public ChargePointsInput(Long userId, int chargePoint) {
        this.userId = userId;
        this.chargePoint = chargePoint;
    }
}
