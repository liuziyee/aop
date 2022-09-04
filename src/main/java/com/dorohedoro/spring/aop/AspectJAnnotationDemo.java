package com.dorohedoro.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@Aspect
@Configuration
@EnableAspectJAutoProxy
public class AspectJAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = null;
        try {
            context = new AnnotationConfigApplicationContext(AspectJAnnotationDemo.class);

            AspectJAnnotationDemo bean = context.getBean(AspectJAnnotationDemo.class);
            log.info("{}", bean.getClass());
        } finally {
            context.close();
        }
    }
}
