package com.green.greengram3.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //설정과 관련된 //e.g. swagger
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Greengram Ver.3")
                        .description("인스타그램 콜론 코딩 v3")
                        .version("2.0.0")
                );
    }
}
