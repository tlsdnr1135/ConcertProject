package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueInput;
import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueOutput;
import com.hhp.concertreservation.B_application.repository.queue.QueueItemRepository;
import com.hhp.concertreservation.B_application.repository.queue.QueueRepository;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueueServiceTest {

    @Mock
    QueueRepository queueRepository;

    @Mock
    QueueItemRepository queueItemRepository;

    @InjectMocks
    QueueService queueService;

    @Test
    @DisplayName("성공_대기열 조회_대기열 ACTIVE 상태인 유저가 최대가 아닌 경우 (19/20)")
    void success_selectRemainingTimeInQueue() {
        //given
        Long concertId = 5L;
        String token = "123-123";

        int currentActiveUserCount = 19;

        Queue queue = Queue.builder()
                .id(1L)
                .concertId(concertId)
                .maxActiveUser(20)
                .build();

        RemainingTimeInQueueInput input = RemainingTimeInQueueInput.builder()
                .token(token)
                .concertId(concertId)
                .build();

        RemainingTimeInQueueOutput expectOutput = RemainingTimeInQueueOutput.builder()
                .existWaitingQueue(false)
                .queueRank(0)
                .waitingSecond(0)
                .build();

        when(queueRepository.findQueueByConcertId(concertId)).thenReturn(Optional.of(queue));
        when(queueItemRepository.getQueueActiveUserCount(concertId)).thenReturn(currentActiveUserCount);


        //when
        RemainingTimeInQueueOutput output = queueService.selectRemainingTimeInQueue(input);

        //then
        assertNotNull(output);
        assertEquals(expectOutput.isExistWaitingQueue(), output.isExistWaitingQueue());
        assertEquals(expectOutput.getQueueRank(), output.getQueueRank());
        assertEquals(expectOutput.getWaitingSecond(), output.getWaitingSecond());

    }

    @Test
    @DisplayName("성공_대기열 조회_대기열 ACTIVE 상태인 유저가 최대인 경우 (20/20)")
    void success_selectRemainingTimeInQueue_Max() {
        //given
        Long concertId = 5L;
        String token = "123-123";

        int currentActiveUserCount = 20;

        Queue queue = Queue.builder()
                .id(1L)
                .concertId(concertId)
                .maxActiveUser(20)
                .build();

        RemainingTimeInQueueInput input = RemainingTimeInQueueInput.builder()
                .token(token)
                .concertId(concertId)
                .build();

        RemainingTimeInQueueOutput expectOutput = RemainingTimeInQueueOutput.builder()
                .existWaitingQueue(true)
                .queueRank(1)
                .waitingSecond(0)
                .build();

        when(queueRepository.findQueueByConcertId(concertId)).thenReturn(Optional.of(queue));
        when(queueItemRepository.getQueueActiveUserCount(concertId)).thenReturn(currentActiveUserCount);
        when(queueItemRepository.getQueueItemWaitingProcedure(token, concertId)).thenReturn(0);


        //when
        RemainingTimeInQueueOutput output = queueService.selectRemainingTimeInQueue(input);

        //then
        assertNotNull(output);
        assertEquals(expectOutput.isExistWaitingQueue(), output.isExistWaitingQueue());
        assertEquals(expectOutput.getQueueRank(), output.getQueueRank());
        assertEquals(expectOutput.getWaitingSecond(), output.getWaitingSecond());

    }

    @Test
    @DisplayName("성공_대기열 조회_대기열 ACTIVE 상태인 유저가 최대이면서 WAITING 상태가 5명이 있을 때")
    void success_selectRemainingTimeInQueue_Max_and_Waiting_5() {
        //given
        Long concertId = 5L;
        String token = "123-123";

        int currentActiveUserCount = 20;

        Queue queue = Queue.builder()
                .id(1L)
                .concertId(concertId)
                .maxActiveUser(20)
                .build();

        RemainingTimeInQueueInput input = RemainingTimeInQueueInput.builder()
                .token(token)
                .concertId(concertId)
                .build();

        RemainingTimeInQueueOutput expectOutput = RemainingTimeInQueueOutput.builder()
                .existWaitingQueue(true)
                .queueRank(6)
                .waitingSecond(0)
                .build();

        when(queueRepository.findQueueByConcertId(concertId)).thenReturn(Optional.of(queue));
        when(queueItemRepository.getQueueActiveUserCount(concertId)).thenReturn(currentActiveUserCount);
        when(queueItemRepository.getQueueItemWaitingProcedure(token, concertId)).thenReturn(5);


        //when
        RemainingTimeInQueueOutput output = queueService.selectRemainingTimeInQueue(input);

        //then
        assertNotNull(output);
        assertEquals(expectOutput.isExistWaitingQueue(), output.isExistWaitingQueue());
        assertEquals(expectOutput.getQueueRank(), output.getQueueRank());
        assertEquals(expectOutput.getWaitingSecond(), output.getWaitingSecond());

    }
}