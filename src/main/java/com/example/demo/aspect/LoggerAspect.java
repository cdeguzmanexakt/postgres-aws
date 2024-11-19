package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);
    @Around(value = "execution(* com.example.demo.controller.*.*(..))")
    public Object LogApiCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Starting method execution for ={{}}", joinPoint.getSignature().getName());
        LOGGER.info("Processing request for {}, details={{}}", joinPoint.getSignature().getName(), joinPoint.getArgs());
        Object result = joinPoint.proceed();
        LOGGER.info("ACCEPTED response is generated, details = {{}}", result);
        LOGGER.info("Finished execution of ={{}}", joinPoint.getSignature().getName());
        return result;
    }
}
