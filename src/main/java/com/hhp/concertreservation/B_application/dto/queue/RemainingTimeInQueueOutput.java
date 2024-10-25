package com.hhp.concertreservation.B_application.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemainingTimeInQueueOutput {

    private boolean existWaitingQueue = false;

    private int queueRank = 0;

    private int waitingSecond = 0;

    @Builder
    public RemainingTimeInQueueOutput(boolean existWaitingQueue, int queueRank, int waitingSecond) {
        this.existWaitingQueue = existWaitingQueue;
        this.queueRank = queueRank;
        this.waitingSecond = waitingSecond;
    }
}
