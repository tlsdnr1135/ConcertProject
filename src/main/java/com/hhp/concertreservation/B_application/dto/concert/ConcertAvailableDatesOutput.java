package com.hhp.concertreservation.B_application.dto.concert;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ConcertAvailableDatesOutput {

    private List<AvailableDateAndSeat> dateLists;

    @Builder
    public ConcertAvailableDatesOutput(List<AvailableDateAndSeat> dateLists) {
        this.dateLists = dateLists;
    }

    @Getter
    public static class AvailableDateAndSeat{
        private Long id;
        private LocalDateTime date;
        private int maxSeatCnt;

        @Builder
        public AvailableDateAndSeat(Long id, LocalDateTime date, int maxSeatCnt) {
            this.id = id;
            this.date = date;
            this.maxSeatCnt = maxSeatCnt;
        }
    }
}
