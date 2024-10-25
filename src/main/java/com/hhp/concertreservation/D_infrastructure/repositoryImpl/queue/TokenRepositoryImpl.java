package com.hhp.concertreservation.D_infrastructure.repositoryImpl.queue;

import com.hhp.concertreservation.B_application.repository.queue.TokenRepository;
import com.hhp.concertreservation.C_domain.queue.entity.Token;
import com.hhp.concertreservation.D_infrastructure.jpa.queue.TokenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;

    @Override
    public void save(Token token) {
        tokenJpaRepository.save(token);
    }

    @Override
    public Boolean existsTokenByUserId(Long userId) {
        return tokenJpaRepository.existsByUserId(userId);
    }

    @Override
    public Optional<Token> findTokenByUserId(Long userId) {
        Optional<Token> token = tokenJpaRepository.findByUserId(userId);
        return token;
    }

    @Override
    public Optional<Token> findTokenByToken(String token) {
        return tokenJpaRepository.findByToken(token);
    }

    @Override
    public void deleteTokenByTokenId(Long tokenId) {
        tokenJpaRepository.deleteById(tokenId);
    }
}
