package com.hhp.concertreservation.config.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cont {

    @Operation(summary = "최신 부동산 정책 페이징 조회", description = "최신 부동산 정책 페이징")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/selectPaging")
    public ResponseEntity<?> selectRealEstateLatestPolicyPaging(@RequestParam("page") int page){



        return ResponseEntity.ok(null);
    }

}
