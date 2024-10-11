package com.hhp.concertreservation.presantation.dto.payment;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SelectPointsRes {

    private int point = 0;


    @Builder
    public SelectPointsRes(int point) {
        this.point = point;
    }
}
