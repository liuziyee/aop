package com.dorohedoro.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class DefaultAspectConfiguration {

    @Pointcut("execution(public * *(..))")
    public void anyPublicMethod() {
    }

    @Around("anyPublicMethod()")
    public Object aroundAnyPublicMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("@Around {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Before("anyPublicMethod()")
    public void beforeAnyPublicMethod(JoinPoint joinPoint) {
        log.info("@Before {}", joinPoint.getSignature());
    }
}
