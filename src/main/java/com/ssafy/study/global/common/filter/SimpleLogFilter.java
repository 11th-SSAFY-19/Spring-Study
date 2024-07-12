package com.ssafy.study.global.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SimpleLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Log Filter Init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        log.info("요청 url : {}", req.getRequestURL());

        log.info("--- Log Filter Start ---");
        chain.doFilter(request, response);
        log.info("--- Log Filter End ---");
    }

    @Override
    public void destroy() {
        log.info("Log Filter Destroy");
        Filter.super.destroy();
    }
}
