package com.ssafy.study.global.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    //*는 반환 타입을 의미하며, 모든 반환타입을 포함.
    //com.ssafy.study.. -> 'com.ssafy.study' 패키지와 그 하위 모든 패키지를 포함.
    //*(..)는 메서드 이름과 매개변수를 의미. 모든 메서드 이름과 매개변수를 포함.
    @Pointcut("execution(* com.ssafy.study..*(..))")
    public void all(){

    }

    //com.ssafy.study..*Controller -> 'com.ssafy.study' 패키지와 하위 모든 패키지에 Controller로 끝나는 모든 클래스를 포함.
    //*(..)는 메서드 이름과 매개변수를 의미. 모든 메서드 이름과 매개변수를 포함.
    //@Pointcut("execution(public String com.ssafy.study.MyController.someMethod(String))") 으로 와일드 카드없이 사용가능
    @Pointcut("execution(* com.ssafy.study..*Controller.*(..))")
    public void controller(){

    }

    @Pointcut("execution(* com.ssafy.study..*Service.*(..))")
    public void service(){

    }

    @Around("all()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        try{
            Object result = joinPoint.proceed();
            log.info("================== start {} ===================", joinPoint.getSignature());
            return result;
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            log.info("================== end {} in {}ms ===================", joinPoint.getSignature(), timeMs);
        }
    }

    @Before("controller() || service()")
    public void beforeLogic(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("================ {} method start ================", method.getName());

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if(arg != null) {
                log.info("type = {}", arg.getClass().getSimpleName());
                log.info("value = {}", arg);
            }
        }
    }
    @After("controller() || service()")
    public void afterLogic(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        log.info("================ {} method end ===================", method.getName());
    }
}
