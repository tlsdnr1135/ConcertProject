package com.hhp.concertreservation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("콘서트 예매 프로젝트")
                        .description("콘서트 예매 시 대기열 문제 해결")
                        .version("1.0.0"));
    }

}