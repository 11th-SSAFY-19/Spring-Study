package com.ssafy.study.global.common.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Aspect
@Slf4j
@Component
public class DuplicateRequestAspect {
    /**
     *  컨트롤러가 어떤 요청을 받을 때, 그 요청을 requestSet 에 넣고, 요청이 완료되면 없앤다.
     *  이 사이에 중복 요청이 들어오면, requestSet 을 확인하여 중복인지 체크
     *  중복이면 error / 아니면 requestSet 에 추가
     */

    private static final long REQUEST_EXPIRATION_TIME = 5;  // 요청 만료 시간

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    private Set<String> requestSet = Collections.synchronizedSet(new HashSet<>());

    @Pointcut("within(*..*Controller)")
    public void onRequest() {}

    @Around("onRequest()")
    public Object duplicateRequestCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();

        // GET method 는 중복 체크 안함 (할 필요가 없기 때문)
        if ("GET".equalsIgnoreCase(httpMethod)) {
            return joinPoint.proceed();
        }

        String requestId = joinPoint.getSignature().toLongString();

        // [redis 방식]
        // 중복 요청인 경우
        if (isDuplicateRequest(requestId)) {
            return handleDuplicateRequest();
        }

        try {
            return joinPoint.proceed();
        } finally {
            // 실행 완료 후 삭제
            removeRequest(requestId);
        }

        // [단순 aop 적용 방식]
        /**
        // 중복 요청인 경우
        if (requestSet.contains(requestId)) {
            return handleDuplicateRequest();
        }

        requestSet.add(requestId);

        try {
            return joinPoint.proceed();
        } finally {
            // 실행 완료 후 삭제
            requestSet.remove(requestId);
        }
         */
    }

    // redis 로 중복요청인지 확인
    private boolean isDuplicateRequest(String requestId) {
        Boolean isAbsent = redisTemplate.opsForValue().setIfAbsent(requestId, "in redis", REQUEST_EXPIRATION_TIME, TimeUnit.SECONDS);
        return isAbsent == null || !isAbsent;
    }

    // redis 로 요청 지우기
    private void removeRequest(String requestId) {
        redisTemplate.delete(requestId);
    }

    // 중복 요청에 대한 응답 처리
    private ResponseEntity<Object> handleDuplicateRequest() {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("중복된 요청입니다.");
    }
}
