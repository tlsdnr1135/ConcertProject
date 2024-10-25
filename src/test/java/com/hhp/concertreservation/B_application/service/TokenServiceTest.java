package com.hhp.concertreservation.B_application.service;

import com.hhp.concertreservation.B_application.dto.queue.*;
import com.hhp.concertreservation.B_application.repository.queue.QueueItemRepository;
import com.hhp.concertreservation.B_application.repository.queue.QueueRepository;
import com.hhp.concertreservation.B_application.repository.queue.TokenRepository;
import com.hhp.concertreservation.C_domain.queue.entity.Queue;
import com.hhp.concertreservation.C_domain.queue.entity.QueueItem;
import com.hhp.concertreservation.C_domain.queue.entity.Token;
import com.hhp.concertreservation.F_common.SystemClockHolder;
import com.hhp.concertreservation.F_common.SystemUuidHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {
    @Mock
    SystemClockHolder systemClockHolder;

    @Mock
    SystemUuidHolder systemUuidHolder;

    @Mock
    TokenRepository tokenRepository;

    @Mock
    QueueRepository queueRepository;

    @Mock
    QueueItemRepository queueItemRepository;

    @InjectMocks
    TokenService tokenService;

    @Test
    @DisplayName("성공_토큰 발급")
    void success_issueToken() {
        LocalDateTime nowMinutes = LocalDateTime.now();
        LocalDateTime plusMinutes = LocalDateTime.now().plusMinutes(5);
        String uuid = "123-456";

        //given
        IssueTokenInput input = IssueTokenInput.builder()
                .concertId(1L)
                .userId(1L)
                .build();

        Token token = Token.builder()
                .token(uuid)
                .userId(input.getUserId())
                .expiredDate(plusMinutes)
                .build();

        Queue queue = Queue.builder()
                .id(1L)
                .concertId(input.getConcertId())
                .maxActiveUser(20)
                .build();

        IssueTokenOutput expectedOutput = IssueTokenOutput.builder()
                .token(uuid)
                .build();

        when(systemUuidHolder.random()).thenReturn(uuid);
        when(systemClockHolder.plusFiveMinMillis()).thenReturn(plusMinutes);
        when(systemClockHolder.nowMillis()).thenReturn(nowMinutes);

        when(tokenRepository.existsTokenByUserId(input.getUserId())).thenReturn(false);
        when(queueRepository.findQueueByConcertId(input.getConcertId())).thenReturn(Optional.of(queue));
        when(queueItemRepository.existsQueueItemByTokenAndQueueId(token.getToken(), queue.getId())).thenReturn(false);


        //when
        IssueTokenOutput output = tokenService.issueToken(input);

        //then
        assertEquals(expectedOutput.getToken(), output.getToken());
        verify(tokenRepository, times(1)).save(any(Token.class));
        verify(queueItemRepository, times(1)).save(any(QueueItem.class));

    }

    @Test
    @DisplayName("성공_토큰 재발급")
    void success_reissueToken() {
        LocalDateTime nowMinutes = LocalDateTime.now();
        LocalDateTime plusMinutes = LocalDateTime.now().plusMinutes(5);
        String uuid = "123-456";

        //given
        ReissueTokenInput input = ReissueTokenInput.builder()
                .concertId(1L)
                .userId(1L)
                .build();

        Token token = Token.builder()
                .token(uuid)
                .build();

        Queue queue = Queue.builder().build();

        QueueItem queueItem = QueueItem.builder().build();

        ReissueTokenOutput expectedOutput = ReissueTokenOutput.builder()
                .token(uuid)
                .build();

        when(systemUuidHolder.random()).thenReturn(uuid);
        when(systemClockHolder.plusFiveMinMillis()).thenReturn(plusMinutes);
        when(systemClockHolder.nowMillis()).thenReturn(nowMinutes);

        when(tokenRepository.findTokenByUserId(input.getUserId())).thenReturn(Optional.of(token));
        when(queueRepository.findQueueByConcertId(input.getConcertId())).thenReturn(Optional.of(queue));
        when(queueItemRepository.findQueueItemByTokenAndQueueId(token.getToken(), queue.getId())).thenReturn(Optional.of(queueItem));


        //when
        ReissueTokenOutput output = tokenService.reissueToken(input);

        //then
        assertEquals(expectedOutput.getToken(), output.getToken());
        verify(tokenRepository, times(1)).save(any(Token.class));
        verify(queueItemRepository, times(1)).save(any(QueueItem.class));

    }

    @Test
    @DisplayName("성공_토큰 삭제")
    void success_deleteToken() {
        //given
        DeleteTokenInput input = DeleteTokenInput.builder().build();

        Token token = Token.builder().build();
        Queue queue = Queue.builder().build();
        QueueItem queueItem = QueueItem.builder().build();

        when(queueRepository.findQueueByConcertId(input.getConcertId())).thenReturn(Optional.of(queue));
        when(queueItemRepository.findQueueItemByTokenAndQueueId(input.getToken(), queue.getId())).thenReturn(Optional.of(queueItem));
        when(tokenRepository.findTokenByToken(input.getToken())).thenReturn(Optional.of(token));


        //when
        tokenService.deleteToken(input);

        //then
        verify(tokenRepository, times(1)).deleteTokenByTokenId(queueItem.getId());
        verify(queueItemRepository, times(1)).deleteQueueItemById(token.getId());

    }
}