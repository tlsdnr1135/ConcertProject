package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueOutput;
import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueInput;
import com.hhp.concertreservation.B_application.repository.queue.QueueItemRepository;
import com.hhp.concertreservation.B_application.repository.queue.QueueRepository;
import com.hhp.concertreservation.B_application.repository.queue.TokenRepository;
import com.hhp.concertreservation.C_domain.enums.QueueStatus;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import com.hhp.concertreservation.C_domain.queue.entity.Token;
import com.hhp.concertreservation.F_common.SystemClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueueService {

    private final QueueRepository queueRepository;
    private final QueueItemRepository queueItemRepository;
    private final TokenRepository tokenRepository;

    /**
     * 이미 대기열이 가득 찼는지
     */
    public boolean isQueueMax(Long concertId) {
        Queue queue = queueRepository.findQueueByConcertId(concertId).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("가지고 있는 Queue테이블이 없습니다.")
        );
        int currentActiveUserCount = queueItemRepository.getQueueActiveUserCount(queue.getConcertId());

        return queue.isQueueMaxActiveUser(currentActiveUserCount);
    }

    /**
     * 대기열이 가득 찼을 경우 내 순위가 몇순위 인지
     */
    public int getQueueRank(Long concertId, String tokenUUID) {

        int queueRank = queueItemRepository.getWaitingRank(tokenUUID, concertId);

        return queueRank + 1;
    }

    /**
     * 대기열 진입 (대기열 상태 변경)
     */
    @Transactional
    public void entryQueue(String tokenUUID, Long queueId) {
        //WAITING -> ACTIVE
        QueueItem queueItem = queueItemRepository.findQueueItemByTokenAndQueueId(tokenUUID, queueId).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당하는 대기열이 없습니다.")
        );
        queueItem.changeStatus(QueueStatus.ACTIVE);
    }

    /**
     * 대기열 해제
     */
    public void cancelQueue(Long concertId, String tokenUUID) {
        //삭제할 대기열이 있는지 확인 후 삭제.
        Queue queue = queueRepository.findQueueByConcertId(concertId).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당하는 대기열이 없습니다.")
        );
        QueueItem queueItem = queueItemRepository.findQueueItemByTokenAndQueueId(tokenUUID, queue.getId()).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당 대기열이 존재하지 않습니다.")
        );
        queueItemRepository.deleteQueueItemById(queueItem.getId());

        //삭제할 토큰이 있는지 확인.
        Token token = tokenRepository.findTokenByToken(tokenUUID).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("해당하는 토큰이 없습니다.")
        );
        tokenRepository.deleteTokenByTokenId(token.getId());
    }
}
