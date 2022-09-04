package com.dorohedoro.spring.aop;

import com.dorohedoro.intro.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
@Configuration
public class AspectJXmlDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = null;
        try {
            context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");
            EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);
            log.info("{}", echoService.getClass());
            log.info("{}", echoService.echo("Hello,World"));
        } finally {
            context.close();
        }
    }
}
