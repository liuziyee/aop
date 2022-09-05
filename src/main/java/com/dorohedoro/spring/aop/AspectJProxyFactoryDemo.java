package com.dorohedoro.spring.aop;

import com.dorohedoro.spring.aop.aspect.DefaultAspectConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AspectJProxyFactoryDemo {
    public static void main(String[] args) {
        Map<String, Object> cache = new HashMap<>();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        proxyFactory.addAspect(DefaultAspectConfiguration.class);

        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    log.info("key: {}, value: {}", args[0], args[1]);
                }
            }
        });

        Map<String, Object> proxy = proxyFactory.getProxy();
        log.info("{}", proxy.getClass());
        proxy.put("no", 117);
        log.info("{}", proxy.get("no"));
    }
}
