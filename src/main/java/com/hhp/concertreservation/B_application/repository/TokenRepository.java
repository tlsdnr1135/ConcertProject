package com.hhp.concertreservation.B_application.repository;

import com.hhp.concertreservation.C_domain.queue.entity.Token;

import java.util.UUID;

public interface TokenRepository {

    Boolean existsTokenByUserId(UUID userId);

    void save(Token token);



}
