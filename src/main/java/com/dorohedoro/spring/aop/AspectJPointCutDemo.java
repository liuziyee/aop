package com.dorohedoro.spring.aop;

import com.dorohedoro.spring.aop.aspect.DefaultAspectConfiguration;
import com.dorohedoro.spring.aop.aspect.LogAspectConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@EnableAspectJAutoProxy
public class AspectJPointCutDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = null;
        try {
            context = new AnnotationConfigApplicationContext();
            context.register(AspectJPointCutDemo.class, DefaultAspectConfiguration.class, LogAspectConfiguration.class);
            context.refresh();

            AspectJPointCutDemo bean = context.getBean(AspectJPointCutDemo.class);
            bean.execute();
        } finally {
            context.close();
        }
    }
    
    public void execute() {
        log.info("execute()...");
    }
}
