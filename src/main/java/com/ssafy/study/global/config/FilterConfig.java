package com.ssafy.study.global.config;

import com.ssafy.study.global.common.filter.SimpleLogFilter;
import com.ssafy.study.global.common.filter.SimpleTimerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean simpleLogFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SimpleLogFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean simpleTimerFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SimpleTimerFilter());
        registrationBean.addUrlPatterns("/api/episodes/*", "/api/comments/*");
        return registrationBean;
    }

}
