package com.ssafy.study.global.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            printStartLog(request);
            filterChain.doFilter(request, response);
            printFinishLog();
        } finally {
            MDC.clear();
        }

    }

    private static void printStartLog(HttpServletRequest request) {
        String traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);
        MDC.put("startMillis", String.valueOf(System.currentTimeMillis()));

        // TODO 원본 client의 ip를 얻는법 생각
        String clientIp = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String requestURI = request.getRequestURI();
        MDC.put("clientIp", clientIp);
        MDC.put("userAgent", userAgent);
        MDC.put("requestURI", requestURI);

        log.info("request start");
    }

    private static void printFinishLog() {
        long usedTime = System.currentTimeMillis() - Long.parseLong(MDC.get("startMillis"));

        MDC.put("spentTime(ms)", String.valueOf(usedTime));
        log.info("request end");
    }
}
