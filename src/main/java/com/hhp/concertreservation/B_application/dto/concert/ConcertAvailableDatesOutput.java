package com.hhp.concertreservation.B_application.dto.concert;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ConcertAvailableDatesOutput {

    private List<AvailableDateAndSeatInfo> dateLists;

    @Builder
    public ConcertAvailableDatesOutput(List<AvailableDateAndSeatInfo> dateLists) {
        this.dateLists = dateLists;
    }

    @Getter
    public static class AvailableDateAndSeatInfo {
        private Long id;
        private LocalDateTime date;
        private int maxSeatCnt;

        @Builder
        public AvailableDateAndSeatInfo(Long id, LocalDateTime date, int maxSeatCnt) {
            this.id = id;
            this.date = date;
            this.maxSeatCnt = maxSeatCnt;
        }
    }
}
