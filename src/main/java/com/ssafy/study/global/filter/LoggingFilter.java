package com.ssafy.study.global.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        doFilterWithWrapped(
                requestWrapper,
                responseWrapper,
                filterChain
        );
    }

    private void doFilterWithWrapped(
            ContentCachingRequestWrapper requestWrapper,
            ContentCachingResponseWrapper responseWrapper,
            FilterChain filterChain
    ) {
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        try {
            String requestId = UUID.randomUUID().toString().substring(0, 10);
            MDC.put("requestId", requestId);
            startTime = System.currentTimeMillis();

            filterChain.doFilter(requestWrapper, responseWrapper);
        } catch (Exception e) {
            log.error("requestId: {}, [log first error message: {}]", MDC.get("requestId"), e.getMessage());
        } finally {
            endTime = System.currentTimeMillis();
            try {
                logRequest(requestWrapper);
                logResponse(responseWrapper, (endTime - startTime) / 1000.0);
            } catch (Exception e) {
                log.error("requestId: {}, [log second error message: {}]", MDC.get("requestId"), e.getMessage());
            }
            try {
                responseWrapper.copyBodyToResponse();
            } catch (IOException e) {
                log.error("requestId: {}, 복사 실패", MDC.get("requestId"));
            }

            MDC.remove("requestId");
        }
    }

    private void logRequest(ContentCachingRequestWrapper requestWrapper) throws IOException {
        String contentType = checkContentType(requestWrapper.getContentType());
        String body = getBody(requestWrapper, contentType);

        log.info("[requestId: {}, method: {}, url: {}, contentType: {}, body: [{}]]",
                MDC.get("requestId"),
                requestWrapper.getMethod(),
                getRequestURI(requestWrapper),
                contentType,
                body
        );
    }

    private String getBody(ContentCachingRequestWrapper requestWrapper, String contentType) throws IOException {
        if (contentType.contains("multipart/form-data")) {
            return getMultipartContent(requestWrapper);
        }
        return getJsonString(requestWrapper.getContentAsByteArray());
    }

    private String getMultipartContent(ContentCachingRequestWrapper requestWrapper) {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(
                requestWrapper.getServletContext());
        MultipartResolver multipartResolver = context.getBean(MultipartResolver.class);
        MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(requestWrapper);
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        StringBuilder mapToString = new StringBuilder("{");

        for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            MultipartFile file = entry.getValue();
            mapToString.append(
                    String.format(
                            "\"%s\": \"%s (size: %s)\",",
                            entry.getKey(), file.getOriginalFilename(), file.getSize()
                    )
            );
        }

        return mapToString.substring(0, mapToString.length() - 1) + "}";
    }

    private static String checkContentType(String contentType) {
        if (contentType == null) {
            return "application/json";
        }
        return contentType;
    }

    private String getRequestURI(ContentCachingRequestWrapper requestWrapper) {
        String queryString = requestWrapper.getQueryString();
        if (queryString == null) {
            return requestWrapper.getRequestURI();
        }
        return requestWrapper.getRequestURI() + "?" + queryString;
    }

    private void logResponse(ContentCachingResponseWrapper responseWrapper, Double elapsedTime) throws JsonProcessingException {
        log.info("Response [requestId: {}, elapsedTime: {}, Payload: {}]",
                MDC.get("requestId"),
                elapsedTime,
                getJsonString(responseWrapper.getContentAsByteArray())
        );
    }

    private static String getJsonString(byte[] rowData) throws JsonProcessingException {
        if (rowData == null || rowData.length == 0) {
            return "";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = objectMapper.readValue(new String(rowData, StandardCharsets.UTF_8), Object.class);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
    }
}
