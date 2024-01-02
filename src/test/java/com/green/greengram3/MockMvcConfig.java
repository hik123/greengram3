package com.green.greengram3;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AutoConfigureMockMvc
@Import({ MockMvcConfig.MockMvc.class })
public @interface MockMvcConfig {
    class MockMvc {
        @Bean
        MockMvcBuilderCustomizer utf8Config() {         //utf-8 인코딩 세팅
            return builder -> builder.addFilter(new CharacterEncodingFilter("UTF-8", true)); // -> 애로우펑션, 메소드호출
        }
    }
}
