package com.hhp.concertreservation.B_application.dto.concert;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ConcertAvailableSeatOutput {

    private int availableSeatCnt;
    private int totalSeatCnt;
    private List<SeatInfo> seatInfoList;

    @Builder
    public ConcertAvailableSeatOutput(int availableSeatCnt, int totalSeatCnt, List<SeatInfo> seatInfoList) {
        this.availableSeatCnt = availableSeatCnt;
        this.totalSeatCnt = totalSeatCnt;
        this.seatInfoList = seatInfoList;
    }

    @Getter
    public static class SeatInfo {
        private Long seatId;
        private String seatNumber;
        private String status;

        @Builder
        public SeatInfo(Long seatId, String seatNumber, String status) {
            this.seatId = seatId;
            this.seatNumber = seatNumber;
            this.status = status;
        }
    }

}
