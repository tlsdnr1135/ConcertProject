package com.hhp.concertreservation.B_application.repository;

import com.hhp.concertreservation.C_domain.queue.Queue;
import com.hhp.concertreservation.C_domain.queue.QueueItem;
import com.hhp.concertreservation.C_domain.queue.Token;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository {

    Boolean existsTokenByUserId(UUID userId);

    void save(Token token);



}
