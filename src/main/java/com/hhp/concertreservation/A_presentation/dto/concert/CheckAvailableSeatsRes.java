package com.hhp.concertreservation.A_presentation.dto.concert;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CheckAvailableSeatsRes {

    private int availableSeatCnt;
    private int totalSeatCnt;
    private List<seatInfo> seatInfoList;

    @Builder
    public CheckAvailableSeatsRes(int availableSeatCnt, int totalSeatCnt, List<seatInfo> seatInfoList) {
        this.availableSeatCnt = availableSeatCnt;
        this.totalSeatCnt = totalSeatCnt;
        this.seatInfoList = seatInfoList;
    }

    @Getter
    public static class seatInfo{
        private Long seatId;
        private String seatNumber;
        private String status;

        @Builder
        public seatInfo(Long seatId, String seatNumber, String status) {
            this.seatId = seatId;
            this.seatNumber = seatNumber;
            this.status = status;
        }
    }

}
