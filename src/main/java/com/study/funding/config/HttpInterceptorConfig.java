package com.study.funding.config;

import com.study.funding.interceptor.HttpInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class HttpInterceptorConfig implements WebMvcConfigurer {

    private final HttpInterceptor httpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor)
                .order(1)
                .addPathPatterns("/products/*/funding")
                .addPathPatterns("/products/member/fundingList");
    }
}
