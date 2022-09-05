package com.dorohedoro.spring.aop;

import com.dorohedoro.intro.DefaultEchoService;
import com.dorohedoro.intro.EchoService;
import com.dorohedoro.spring.aop.pointcut.ClassAndMethodFilterPointcut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
public class PointcutAPIDemo {
    public static void main(String[] args) {
        ClassAndMethodFilterPointcut pointcut = new ClassAndMethodFilterPointcut("echo", EchoService.class);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new DefaultMethodInterceptor());

        DefaultEchoService echoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(echoService);
        proxyFactory.addAdvisor(advisor);

        EchoService proxy = (EchoService) proxyFactory.getProxy();
        log.info("{}", proxy.echo("Hello,World"));
    }
}
