package com.ssafy.study.global.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SimpleTimerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        log.info("요청 url : {}", req.getRequestURL());

        long start = System.currentTimeMillis();

        chain.doFilter(request, response);

        long finish = System.currentTimeMillis();
        long timeMs = finish - start;
        log.info("filter return time = {} ms", timeMs);

        log.info("--- Timer Filter End ---");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
