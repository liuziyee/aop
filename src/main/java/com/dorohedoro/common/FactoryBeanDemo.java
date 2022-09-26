package com.dorohedoro.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class FactoryBeanDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EchoServiceFactoryBean.class);
        context.refresh();

        EchoService echoService = (EchoService) context.getBean("echoServiceFactoryBean");
        log.info("{}", echoService.getClass());
        echoService.echo("Hello,World");

        FactoryBean echoServiceFactoryBean = (FactoryBean) context.getBean("&echoServiceFactoryBean");
        log.info("{}", echoServiceFactoryBean.getClass());
    }
}
