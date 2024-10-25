package com.hhp.concertreservation.A_presentation.controller.queue;

import com.hhp.concertreservation.A_presentation.dto.queue.DeleteTokenReq;
import com.hhp.concertreservation.A_presentation.dto.queue.IssueTokenReq;
import com.hhp.concertreservation.B_application.dto.queue.*;
import com.hhp.concertreservation.B_application.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "토큰")
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    @Operation(summary = "대기열 토큰 발급", description = "대기열 토큰을 발급해주고 대기열에 추가한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = IssueTokenOutput.class)))
    })
    @PostMapping("/issue")
    public ResponseEntity<IssueTokenOutput> issueToken(@RequestBody IssueTokenReq req){

        IssueTokenInput input = IssueTokenInput.builder()
                .concertId(req.getConcertId())
                .userId(req.getUserId())
                .build();

        IssueTokenOutput output = tokenService.issueToken(input);

        return ResponseEntity.ok(output);
    }

    @Operation(summary = "대기열 토큰 재발급", description = "대기열 토큰을 재발급해주고 대기열에 추가한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ReissueTokenOutput.class)))
    })
    @PostMapping("/reissue")
    public ResponseEntity<ReissueTokenOutput> reissueToken(@RequestBody IssueTokenReq req){

        ReissueTokenInput input = ReissueTokenInput.builder()
                .concertId(req.getConcertId())
                .userId(req.getUserId())
                .build();

        ReissueTokenOutput output = tokenService.reissueToken(input);

        return ResponseEntity.ok(output);
    }

    @Operation(summary = "대기열 토큰 삭제", description = "대기열 토큰을 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = IssueTokenOutput.class)))
    })
    @PostMapping("/delete")
    public ResponseEntity<?> deleteToken(@RequestBody DeleteTokenReq req){

        DeleteTokenInput input = DeleteTokenInput.builder()
                .token(req.getToken())
                .concertId(req.getConcertId())
                .build();

        tokenService.deleteToken(input);

        return ResponseEntity.ok(null);
    }



}
