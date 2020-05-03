package com.ems.backend.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Order(0)
@Configuration
public class HandlerInterceptorConfiguration implements WebMvcConfigurer {

    @Value("${ems.service.header-validation-enabled}")
    private boolean headerValidationEnabled;
    @Value("${ems.service.header-validation-urls}")
    private String[] headerValidationUrls = {};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (headerValidationEnabled) {
            registry
                    .addInterceptor(new HeadersRequiringHandlerInterceptor())
                    .addPathPatterns(headerValidationUrls);
        }
    }
}
