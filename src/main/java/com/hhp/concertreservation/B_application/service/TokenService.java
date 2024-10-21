package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.repository.QueueRepository;
import com.hhp.concertreservation.B_application.repository.TokenRepository;
import com.hhp.concertreservation.B_application.repository.UserRepository;
import com.hhp.concertreservation.C_domain.member.User;
import com.hhp.concertreservation.C_domain.queue.entity.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final QueueRepository queueRepository;
    private final QueueService queueService;

    /**
     * 회원 토큰 생성
     */
    @Transactional
    public Token createUserToken(User user, LocalDateTime expiredDate) {
        //TODO validation 이쁘게 바꾸기.
        // 현재 토큰 있는가
        if(tokenRepository.existsTokenByUserId(user.getUserId())) {
            new DuplicateKeyException("이미 토큰이 존재합니다.");
        }

        //TODO expiredDate 생성해주는 애 만들기
        Token token = Token.builder()
                .user(user)
                .expiredDate(expiredDate)
                .build();

        tokenRepository.save(token);

        return token;
    }

    /**
     * 토큰 발급
     */
    @Transactional
    public UUID issueToken(UUID userId, Long concertId) {
        // 회원 조회
        User user = userRepository.findUserByUserId(userId);

        Token token = createUserToken(user, LocalDateTime.now());

//        concertId

//        queueService.addQueue(token, );

        return user.getUserId();
    }

}
