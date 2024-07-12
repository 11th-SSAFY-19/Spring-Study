package com.ssafy.study.global.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        printStartLog(request);

        filterChain.doFilter(request, response);

        printFinishLog(request);
    }

    private static void printFinishLog(HttpServletRequest request) {
        String logIdAttr = request.getAttribute("logId").toString();
        long startMillis = (long) request.getAttribute("startMillis");
        long usedTime = System.currentTimeMillis() - startMillis;
        log.info("request_id={}, used_time={}ms", logIdAttr, usedTime);
    }

    private static void printStartLog(HttpServletRequest request) {
        String logId = UUID.randomUUID().toString();
        request.setAttribute("logId", logId);
        request.setAttribute("startMillis", System.currentTimeMillis());
        log.info("request_id={}", logId);
    }
}
