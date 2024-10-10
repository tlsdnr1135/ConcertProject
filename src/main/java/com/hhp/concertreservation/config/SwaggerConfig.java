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
                        .title("세이프한 집구하기")
                        .description("세이프하게 집구하는 방법, 세집!")
                        .version("1.0.0"));
    }

}
