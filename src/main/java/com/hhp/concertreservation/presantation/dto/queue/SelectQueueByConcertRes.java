package com.hhp.concertreservation.presantation.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SelectQueueByConcertRes {

    private boolean existWaitingQueue = false;

    private int queueRank = 0;

    private int waitingSecond = 0;

    @Builder
    public SelectQueueByConcertRes(boolean existWaitingQueue, int queueRank, int waitingSecond) {
        this.existWaitingQueue = existWaitingQueue;
        this.queueRank = queueRank;
        this.waitingSecond = waitingSecond;
    }
}
