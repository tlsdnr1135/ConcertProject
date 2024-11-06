package com.hhp.concertreservation.B_application.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EntryQueueInput {

    String token;
    Long queueId;

    @Builder
    public EntryQueueInput(String token, Long queueId) {
        this.token = token;
        this.queueId = queueId;
    }
}
