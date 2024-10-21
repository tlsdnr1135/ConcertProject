package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.A_presentation.dto.queue.SelectQueueByConcertRes;
import com.hhp.concertreservation.B_application.repository.QueueRepository;
import com.hhp.concertreservation.B_application.repository.TokenRepository;
import com.hhp.concertreservation.B_application.repository.UserRepository;
import com.hhp.concertreservation.C_domain.member.User;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import com.hhp.concertreservation.C_domain.queue.entity.QueueStatus;
import com.hhp.concertreservation.C_domain.queue.entity.Token;
import com.hhp.concertreservation.F_common.SystemClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QueueService {

    private final QueueRepository queueRepository;

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final SystemClockHolder systemClockHolder;

    /**
     * 대기열 추가, 토큰 발급
     */
    @Transactional
    public UUID issueToken(UUID userId, Long concertId) {
        // 회원 조회
        User user = userRepository.findUserByUserId(userId);

        //토큰 조회
        if(tokenRepository.existsTokenByUserId(user.getUserId())) {
            new DuplicateKeyException("이미 토큰이 존재합니다.");
        }

        Token token = Token.builder()
                .user(user)
                .expiredDate(systemClockHolder.millis())
                .build();
        tokenRepository.save(token);

        Queue queue = queueRepository.findQueueByConcertId(concertId);

        //대기열 조회 API 호출


        QueueItem queueItem = QueueItem.builder()
                .queue(queue)
                .token(token)
                .status(QueueStatus.WAIT)
                .build();
        queueRepository.save(queueItem);

        return user.getUserId();
    }

    /**
     *  필터에서 할일들
     *  1. 토큰 헤더에 가지고 있는지 확인
     *  1-1. 토큰 헤더에 없으면... 토큰 발급 과정 -> 토큰 발급된 적 있는지 확인, 토큰 발급된 적 있으면 삭제하고 재발급, 없으면 발급.
     *  1-2. 토큰 헤더에 있으면... 토큰 발급 과정 -> 토큰이 이미 있는지 확인, 토큰이 유효한지 확인, 만료됐는지 확인, 괜찮으면 통과 //
     *                                                                                                 하나라도 이상하면 재발급 //
     */

    /**
     * 대기열 조회 (결국 여기 오는 애들은 온전한 애들만 온다 이걸 POLICY로 옮겨도 될거같음.
     */
    public SelectQueueByConcertRes getQueue(UUID userId, Long concertId) {
        //최대 인원인지도 확인 해봐야함.
        Queue queue = queueRepository.findQueueByConcertId(concertId);
        int queueActiveUserCount = queueRepository.getQueueActiveUserCount(concertId);

        boolean existWaitingQueue = false;

        // 최대인지 확인.
        if(!queue.checkMaxActiveQueue(queueActiveUserCount)){
            //최대 아니면
            return SelectQueueByConcertRes.builder()
                    .existWaitingQueue(existWaitingQueue)
                    .queueRank(queueActiveUserCount)
                    .waitingSecond(0)
                    .build();
        }

        // 최대일 경우 순서
        int queueItemWaitingUserCount = queueRepository.getQueueItemWaitingProcedure(userId, concertId);
        existWaitingQueue = true;

        return SelectQueueByConcertRes.builder()
                .existWaitingQueue(existWaitingQueue)
                .queueRank(queueItemWaitingUserCount)
                .waitingSecond(0)
                .build();
    }

    /**
     * 대기열 조회
     */
    public SelectQueueByConcertRes getQueueTest(UUID userId, Long concertId) {
        // 토큰 안가지고 있는사람
        /**
         * 대기열 조회 (현재)
         * -> 토큰 발급
         * -> 다시조회
         */
        // 토큰 가지고 있는 사람
        /**
         * 대기열 조회 (현재)
         * 내가 현재 몇 번 째 인지 조회
         */
        // 토큰 가지고 있는데 만료
        /**
         * 대기열 조회 전에 재발급 요청. (필터)
         */
        // 토큰 가지고 있는데 다시 들어온 사람. 맨뒤로 보내기?
        /**
         * 대기열 조회 전에 재발급 요청. (필터)
         */

        Queue queue = queueRepository.findQueueByConcertId(concertId);
        int queueActiveUserCount = queueRepository.getQueueActiveUserCount(concertId);

        //대기열 없으면 바로 입장.
        if(queueActiveUserCount < queue.getMaxActiveUser()) {
            return SelectQueueByConcertRes.builder()
                    .existWaitingQueue(false)
                    .queueRank(0)
                    .waitingSecond(0)
                    .build();
        }

        //대기열 조회
        //유효한 회원인지 확인.
        User user = userRepository.findUserByUserId(userId);

        //토큰 유무
        if(tokenRepository.existsTokenByUserId(user.getId())) {
            //1. 토큰 가지고 있으면? -> 현재 자신의 대기열을 확인.



        }else{
            //2. 토큰 안가지고 있으면? -> 전체 대기열 확인.
            // 현재 몇명이 기다리고 있는지 확인.

        }








        return null;
    }

    /**
     *
     */










    /**
     * 토큰 발급
     */
    @Transactional
    public UUID issueToken_VER2(UUID userId, Long concertId) {
        // 회원 조회
        User user = userRepository.findUserByUserId(userId);

        Token token = createUserToken(user, LocalDateTime.now());
        Queue queue = queueRepository.findQueueByConcertId(concertId);

        addQueue(token, queue);

        return user.getUserId();
    }

    /**
     * 대기열 추가
     */
    public void addQueue(Token token, Queue queue) {

        QueueItem queueItem = QueueItem.builder()
                .queue(queue)
                .token(token)
                .build();

        queueRepository.save(queueItem);
    }


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
     * 대기열 조회(폴링)
     */
    public List<Objects> getQueue() {

        // 맨 처음 대기열 확인.

        //
        return null;
    }
}
