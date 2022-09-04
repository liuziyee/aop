package com.dorohedoro.intro;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

@Slf4j
public class JdkDynamicProxyDemo {
    public static void main(String[] args) {
        MDC.put("TIMESTAMP", String.valueOf(System.currentTimeMillis()));
        MDC.put("LOG_ID", UUID.randomUUID().toString().replace("-", ""));
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    EchoService target = new DefaultEchoService();
                    return method.invoke(target, args[0]);
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        log.info("{}", echoService.getClass());
        log.info("{}", echoService.echo("Hello,World"));
    }
}
