package com.hhp.concertreservation.B_application.facade;

import com.hhp.concertreservation.B_application.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class QueueFacade {

    private final QueueService queueService;

}