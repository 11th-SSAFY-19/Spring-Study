package com.ssafy.study.global.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect     // 이 클래스가 Aspect 클래스임
@Slf4j
@Component
public class SimpleLogAspect {

    // com.ssafy.study 이하, 모든 패키지 이하, 모든 클래스 이하, 모든 메서드에 적용
    @Pointcut("execution(* com.ssafy.study..*(..))")
    public void all() {
    }

    // 컨트롤러 이하 적용
    @Pointcut("execution(* com.ssafy.study..*Controller.*(..))")
    public void controller() {
    }

    // 서비스 이하 적용
    @Pointcut("execution(* com.ssafy.study..*Service.*(..))")
    public void service() {
    }

    // -- 특정 joinpoint 에서 수행될 부가 기능 --
    // all() pointcut 호출 전,후,예외발생 시점에 실행
    @Around("all()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.info("<-- log : {} -->" , joinPoint.getSignature());
            log.info("return time = {} ms", timeMs);
        }
    }

    // 호출 전
    @Before("controller() || service()")
    public void beforeLogic(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("======= method = {} =======", method.getName());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if(arg != null) {
                log.info("request type = {}", arg.getClass().getSimpleName());
                log.info("request value = {}", arg);
            }

        }
    }
    // 호출 후
    @After("controller() || service()")
    public void afterLogic(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("======= method = {} =======", method.getName());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if(arg != null) {
                log.info("response type = {}", arg.getClass().getSimpleName());
                log.info("response value = {}", arg);
            }

        }
    }

}
