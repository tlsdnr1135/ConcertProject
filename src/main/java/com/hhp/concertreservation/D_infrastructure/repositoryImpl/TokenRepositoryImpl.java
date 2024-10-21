package com.hhp.concertreservation.D_infrastructure.repositoryImpl;

import com.hhp.concertreservation.B_application.repository.TokenRepository;
import com.hhp.concertreservation.C_domain.queue.Token;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Override
    public void save(Token token) {

    }

    @Override
    public Boolean existsTokenByUserId(UUID userId) {
        return null;
    }
}
