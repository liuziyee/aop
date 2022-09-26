package com.dorohedoro.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.File;
import java.lang.reflect.Method;

import static org.springframework.cglib.core.DebuggingClassWriter.DEBUG_LOCATION_PROPERTY;

@Slf4j
public class CglibProxyDemo {
    public static void main(String[] args) {
        System.setProperty(DEBUG_LOCATION_PROPERTY, new File("").getAbsolutePath() + "/target/classes");
        Object proxy = Enhancer.create(DefaultEchoService.class, new MethodInterceptor() {
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                if (DefaultEchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    EchoService target = new DefaultEchoService();
                    return method.invoke(target, args);
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        log.info("{}", echoService.getClass());
        log.info("{}", echoService.echo("Hello,World"));
    }
}
