package com.hhp.concertreservation.B_application.facade.queue;

import com.hhp.concertreservation.B_application.dto.queue.EntryQueueInput;
import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueInput;
import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueOutput;
import com.hhp.concertreservation.B_application.dto.token.CancelQueueInput;
import com.hhp.concertreservation.B_application.service.QueueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class QueueFacade {

    private final QueueService queueService;

    /**
     * 콘서트 별 대기열 조회
     */
    public RemainingTimeInQueueOutput selectQueueByConcert(RemainingTimeInQueueInput input){

        boolean isQueueMax = queueService.isQueueMax(input.getConcertId());
        int queueRank = queueService.getQueueRank(input.getConcertId(), input.getToken());

        RemainingTimeInQueueOutput output = RemainingTimeInQueueOutput.builder()
                .isQueueMax(isQueueMax)
                .waitingRank(queueRank)
                .waitingSecond(0)
                .build();
        return output;
    }

    /**
     * 콘서트 별 대기열 진입
     */
    public void entryQueue(EntryQueueInput input) {

        queueService.entryQueue(input.getToken(), input.getQueueId());

    }

    /**
     * 콘서트 별 대기열 해제
     */
    public void cancelQueue(CancelQueueInput input) {

        queueService.cancelQueue(input.getConcertId(), input.getToken());

    }

}
