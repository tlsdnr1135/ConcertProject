package com.hhp.concertreservation.presentation.dto.queue;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddConcertQueueRes {
    private String token;

    @Builder
    public AddConcertQueueRes(String token) {
        this.token = token;
    }
}
