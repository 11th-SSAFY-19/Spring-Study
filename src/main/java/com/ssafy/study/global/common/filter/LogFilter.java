package com.ssafy.study.global.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        log.info("Log Filter : init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("Log Filter : doFilter 실행");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();
        request.setAttribute("logID", uuid);

        try{
            log.info("Log Filter : Request [{}][{}]", uuid, requestURI);
            filterChain.doFilter(request, response);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("Log Filter : Response [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("Log Filter destroy");
    }
}
