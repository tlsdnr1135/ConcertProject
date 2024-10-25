package com.hhp.concertreservation.B_application.dto.point;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SelectPointsInput {

    private Long userId;

    @Builder
    public SelectPointsInput(Long userId) {
        this.userId = userId;
    }
}
