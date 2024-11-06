package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.token.*;
import com.hhp.concertreservation.B_application.repository.queue.QueueItemRepository;
import com.hhp.concertreservation.B_application.repository.queue.QueueRepository;
import com.hhp.concertreservation.B_application.repository.queue.TokenRepository;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import com.hhp.concertreservation.C_domain.enums.QueueStatus;
import com.hhp.concertreservation.C_domain.queue.entity.Token;
import com.hhp.concertreservation.F_common.SystemClockHolder;
import com.hhp.concertreservation.F_common.SystemUuidHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TokenService {

    //TODO 생성자로..
    private final TokenRepository tokenRepository;
    private final QueueRepository queueRepository;
    private final QueueItemRepository queueItemRepository;

    private final SystemClockHolder systemClockHolder;
    private final SystemUuidHolder systemUuidHolder;

    /**
     * 토큰 발급
     */
    @Transactional
    public String issueToken(Long userId, Long concertId) {
        // TODO validation 이쁘게 바꾸기.
        // 현재 토큰 있는가
        if (tokenRepository.existsTokenByUserId(userId)) {
            //TODO EXCEPTION
            throw new RuntimeException("이미 존재하는 토큰");
        }

        //TODO Facade 로 빼보기
        //토큰 발급하고
        Token token = Token.builder()
                .token(systemUuidHolder.random())
                .userId(userId)
                .expiredDate(systemClockHolder.plusFiveMinMillis())
                .build();
        tokenRepository.save(token);

        // TODO validation 이쁘게 바꾸기.
        //대기열 확인해야함
        Queue queue = queueRepository.findQueueByConcertId(concertId).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당하는 대기열이 없습니다.")
        );
        if (queueItemRepository.existsQueueItemByTokenAndQueueId(token.getToken(), queue.getId())) {
            throw new RuntimeException("이미 대기열이 존재합니다.");
        }

        //대기열에 추가.
        QueueItem queueItem = QueueItem.builder()
                .queueId(queue.getId())
                .token(token.getToken())
                .status(QueueStatus.WAITING)
                .createdAt(systemClockHolder.nowMillis())
                .build();

        queueItemRepository.save(queueItem);

        return token.getToken();
    }

    /**
     * 토큰 재발급
     */
    @Transactional
    public ReissueTokenOutput reissueToken(Long userId, Long concertId) {
        //기존 토큰 삭제
        Token token = tokenRepository.findTokenByUserId(userId).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당하는 토큰이 없습니다.")
        );
        tokenRepository.deleteTokenByTokenId(token.getId());

        Queue queue = queueRepository.findQueueByConcertId(concertId).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당하는 대기열이 없습니다.")
        );
        QueueItem queueItem = queueItemRepository.findQueueItemByTokenAndQueueId(token.getToken(), queue.getId()).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당하는 대기열이 없습니다.")
        );
        queueItemRepository.deleteQueueItemById(queueItem.getId());

        //재발급
        //TODO 여기서 옮기자 따로 빼야할듯..?
        Token reToken = Token.builder()
                .token(systemUuidHolder.random())
                .userId(userId)
                .expiredDate(systemClockHolder.plusFiveMinMillis())
                .build();
        tokenRepository.save(reToken);

        //대기열에 추가.
        QueueItem requeueItem = QueueItem.builder()
                .queueId(queue.getId())
                .token(reToken.getToken())
                .status(QueueStatus.WAITING)
                .createdAt(systemClockHolder.nowMillis())
                .build();
        queueItemRepository.save(requeueItem);

        return ReissueTokenOutput.builder()
                .token(token.getToken())
                .build();
    }

}
