package com.dorohedoro.spring.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

@Slf4j
@Aspect
public class LogAspectConfiguration implements Ordered {

    @Before("execution(public * *(..))")
    public void beforeAnyPublicMethod(JoinPoint joinPoint) {
        log.info("@Before logging {}", joinPoint.getSignature());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
