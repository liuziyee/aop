package com.dorohedoro.spring.aop;

import com.dorohedoro.common.DefaultEchoService;
import com.dorohedoro.common.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryBeanDemo {
    @Bean
    public ProxyFactoryBean proxyFactoryBean() {
        DefaultEchoService echoService = new DefaultEchoService();
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(echoService);
        proxyFactoryBean.addAdvice(new DefaultMethodInterceptor());
        return proxyFactoryBean;
    }
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProxyFactoryBeanDemo.class);
        EchoService proxy = (EchoService) context.getBean("proxyFactoryBean");
        log.info("{}", proxy.getClass());
        log.info("{}", proxy.echo("Hello,World"));
    }
    
}
