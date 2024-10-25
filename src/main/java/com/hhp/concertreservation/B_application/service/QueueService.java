package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueOutput;
import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueInput;
import com.hhp.concertreservation.B_application.repository.queue.QueueItemRepository;
import com.hhp.concertreservation.B_application.repository.queue.QueueRepository;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.F_common.SystemClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueueService {

    private final QueueRepository queueRepository;
    private final QueueItemRepository queueItemRepository;
    private final SystemClockHolder systemClockHolder;

    /**
     * 대기열 조회
     */
    public RemainingTimeInQueueOutput selectRemainingTimeInQueue(RemainingTimeInQueueInput input) {
        Queue queue = queueRepository.findQueueByConcertId(input.getConcertId()).orElseThrow(
                //TODO EXCEPTION
                () -> new RuntimeException("가지고 있는 Queue테이블이 없습니다.")
        );
        int currentActiveUserCount = queueItemRepository.getQueueActiveUserCount(input.getConcertId());

        // 최대인지 확인.
        if(!queue.isMaxActiveUser(currentActiveUserCount)){
            //최대 아니면
            return RemainingTimeInQueueOutput.builder()
                    .existWaitingQueue(false)
                    .queueRank(0)
                    .waitingSecond(0)
                    .build();
        }

        // 최대일 경우 순서
        int queueRank = queueItemRepository.getQueueItemWaitingProcedure(input.getToken(), input.getConcertId());

        return RemainingTimeInQueueOutput.builder()
                .existWaitingQueue(true)
                .queueRank(queueRank+1)
                .waitingSecond(0)
                .build();
    }

    /**
     * 대기열 상태 변경
     */
    @Transactional
    public void changeQueueStatus(String token, Long concertId) {
        //WAITING -> ACTIVE
//        QueueItem queueItem = queueRepository.findQueueItemByTokenAndQueueId(token, concertId);
//        queueItem.changeStatus(QueueStatus.ACTIVE);
    }

}
