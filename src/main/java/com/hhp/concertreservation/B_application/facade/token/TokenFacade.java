package com.hhp.concertreservation.B_application.facade.token;

import com.hhp.concertreservation.B_application.dto.token.IssueTokenInput;
import com.hhp.concertreservation.B_application.dto.token.IssueTokenOutput;
import com.hhp.concertreservation.B_application.dto.token.ReissueTokenInput;
import com.hhp.concertreservation.B_application.dto.token.ReissueTokenOutput;
import com.hhp.concertreservation.B_application.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenFacade {

    private final TokenService tokenService;

    /**
     * 대기열 토큰 발급
     */
    public IssueTokenOutput issueToken(IssueTokenInput input) {

        String token = tokenService.issueToken(input.getUserId(), input.getConcertId());

        IssueTokenOutput output = IssueTokenOutput.builder()
                .token(token)
                .build();

        return output;
    }

    /**
     * 대기열 토큰 재발급
     */
    public ReissueTokenOutput reissueToken(ReissueTokenInput input) {
        ReissueTokenOutput token = tokenService.reissueToken(input.getUserId(), input.getConcertId());

        ReissueTokenOutput output = ReissueTokenOutput.builder()
                .token(token.getToken())
                .build();
        return output;
    }

}
