package com.hhp.concertreservation.B_application.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemainingTimeInQueueOutput {

    private boolean isQueueMax = false;

    private int waitingRank = 0;

    private int waitingSecond = 0;

    @Builder
    public RemainingTimeInQueueOutput(boolean isQueueMax, int waitingRank, int waitingSecond) {
        this.isQueueMax = isQueueMax;
        this.waitingRank = waitingRank;
        this.waitingSecond = waitingSecond;
    }
}
