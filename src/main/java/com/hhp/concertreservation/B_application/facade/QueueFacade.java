package com.hhp.concertreservation.B_application.facade;

import com.hhp.concertreservation.B_application.service.QueueService;
import com.hhp.concertreservation.C_domain.member.User;
import com.hhp.concertreservation.C_domain.queue.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class QueueFacade {

    private final QueueService queueService;

}