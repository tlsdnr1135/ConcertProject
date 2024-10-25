package com.hhp.concertreservation.B_application.repository.queue;

import com.hhp.concertreservation.C_domain.queue.entity.Token;

import java.util.Optional;

public interface TokenRepository {

    Boolean existsTokenByUserId(Long userId);
    Optional<Token> findTokenByUserId(Long userId);
    Optional<Token> findTokenByToken(String token);

    void save(Token token);

    void deleteTokenByTokenId(Long tokenId);



}
