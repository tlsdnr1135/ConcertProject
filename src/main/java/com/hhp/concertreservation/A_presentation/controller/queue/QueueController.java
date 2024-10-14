package com.hhp.concertreservation.A_presentation.controller.queue;

import com.hhp.concertreservation.A_presentation.dto.queue.AddConcertQueueReq;
import com.hhp.concertreservation.A_presentation.dto.queue.AddConcertQueueRes;
import com.hhp.concertreservation.A_presentation.dto.queue.SelectQueueByConcertRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "대기열")
@RestController
@RequestMapping("/queue")
public class QueueController {

    @Operation(summary = "콘서트 별 대기열 조회", description = "콘서트 별 대기열 유무, 대기 순위, 대기 시간을 조회해준다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = SelectQueueByConcertRes.class)))
    })
    @GetMapping("/{concertId}")
    public ResponseEntity<?> SelectQueueByConcert(@PathVariable("concertId") int concertId){

        SelectQueueByConcertRes result = SelectQueueByConcertRes.builder()
                .build();

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "콘서트 대기열 추가", description = "대기열 토큰을 발급해주고 대기열에 추가한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = AddConcertQueueRes.class)))
    })
    @PostMapping("/add")
    public ResponseEntity<?> AddConcertQueue(@RequestBody AddConcertQueueReq req){

        AddConcertQueueRes result = AddConcertQueueRes.builder()
                .build();

        return ResponseEntity.ok(result);
    }

}
