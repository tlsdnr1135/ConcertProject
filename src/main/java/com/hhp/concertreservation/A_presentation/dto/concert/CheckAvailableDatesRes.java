package com.hhp.concertreservation.A_presentation.dto.concert;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class CheckAvailableDatesRes {

    private List<AvailableDateAndSeat> dateLists;

    @Builder
    public CheckAvailableDatesRes(List<AvailableDateAndSeat> dateLists) {
        this.dateLists = dateLists;
    }

    @Getter
    public static class AvailableDateAndSeat{
        private Timestamp date;
        private int seatCnt;

        @Builder
        public AvailableDateAndSeat(Timestamp date, int seatCnt) {
            this.date = date;
            this.seatCnt = seatCnt;
        }
    }
}
