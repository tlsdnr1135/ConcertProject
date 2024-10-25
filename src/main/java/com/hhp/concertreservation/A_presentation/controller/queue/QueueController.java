package com.hhp.concertreservation.A_presentation.controller.queue;

import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueInput;
import com.hhp.concertreservation.B_application.dto.queue.RemainingTimeInQueueOutput;
import com.hhp.concertreservation.B_application.service.QueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "대기열")
@RestController
@RequiredArgsConstructor
@RequestMapping("/queues")
public class QueueController {

    private final QueueService queueService;

    @Operation(summary = "콘서트 별 대기열 조회", description = "콘서트 별 대기열 유무, 대기 순위, 대기 시간을 조회해준다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = RemainingTimeInQueueOutput.class)))
    })
    @GetMapping("/{concertId}")
    public ResponseEntity<RemainingTimeInQueueOutput> selectQueueByConcert(@RequestHeader("token") String token, @PathVariable("concertId") Long concertId) {
        RemainingTimeInQueueInput input = RemainingTimeInQueueInput.builder()
                .token(token)
                .concertId(concertId)
                .build();

        RemainingTimeInQueueOutput output = queueService.selectRemainingTimeInQueue(input);

        return ResponseEntity.ok(output);
    }

}
