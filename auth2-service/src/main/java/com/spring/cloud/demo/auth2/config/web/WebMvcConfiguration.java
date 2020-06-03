package com.spring.cloud.demo.auth2.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * WebMvc配置.
 *
 * @author luojm
 * @date 2020/6/2 9:06
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    private static final String[] EXCLUDE_PATH_PATTERNS = {"/api/token/api_token"};


    @Autowired
    private TokenInterceptor tokenInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns(EXCLUDE_PATH_PATTERNS);
    }
}