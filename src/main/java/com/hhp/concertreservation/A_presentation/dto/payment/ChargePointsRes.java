package com.hhp.concertreservation.A_presentation.dto.payment;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChargePointsRes {

    private int point;

    @Builder
    public ChargePointsRes(int point) {
        this.point = point;
    }
}
