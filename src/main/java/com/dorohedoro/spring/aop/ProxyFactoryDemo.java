package com.dorohedoro.spring.aop;

import com.dorohedoro.common.DefaultEchoService;
import com.dorohedoro.common.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyFactoryDemo {
    public static void main(String[] args) {
        DefaultEchoService echoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(echoService);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new DefaultMethodInterceptor());

        EchoService proxy = (EchoService) proxyFactory.getProxy();
        log.info("{}", proxy.getClass());
        log.info("{}", proxy.echo("Hello,World"));
    }
}
