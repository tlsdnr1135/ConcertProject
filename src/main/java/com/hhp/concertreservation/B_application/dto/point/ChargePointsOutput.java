package com.hhp.concertreservation.B_application.dto.point;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChargePointsOutput {

    private int point;

    @Builder
    public ChargePointsOutput(int point) {
        this.point = point;
    }
}
