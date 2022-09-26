package com.dorohedoro.common;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyDemo {
    public static void main(String[] args) {
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
