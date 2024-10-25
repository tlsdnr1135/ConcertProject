package com.hhp.concertreservation.B_application.dto.point;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SelectPointsOutput {

    private int point;

    @Builder
    public SelectPointsOutput(int point) {
        this.point = point;
    }
}
