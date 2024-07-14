package com.ssafy.study.global.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * com.ssafy.study..*.*(..))")
    private void publicMethodFromAllPackage() {
    }

//    @Around(value = "publicMethodFromAllPackage()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//        String methodName = joinPoint.getSignature().getName();
//        log.info(">> {}() - {}", methodName, Arrays.toString(args));
//
//        Object result = joinPoint.proceed();
//        log.info("<< {}() - {}", methodName, result);
//        return result;
//    }

    @Before(value = "publicMethodFromAllPackage()")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        log.info(">> {}() - {}", methodName, Arrays.toString(args));
    }


    @AfterReturning(value = "publicMethodFromAllPackage()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.info("<< {}() - {}", methodName, result);
    }

    @AfterThrowing(pointcut = "publicMethodFromAllPackage()", throwing = "exception")
    public void logAfter(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        log.info("<< {}() - {}", methodName, exception.getMessage());
    }
}
