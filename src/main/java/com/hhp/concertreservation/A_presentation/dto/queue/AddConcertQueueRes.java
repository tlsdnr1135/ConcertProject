package com.hhp.concertreservation.A_presentation.dto.queue;

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