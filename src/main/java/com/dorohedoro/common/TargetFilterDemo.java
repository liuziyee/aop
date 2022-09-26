package com.dorohedoro.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Slf4j
public class TargetFilterDemo {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class clazz = classLoader.loadClass("com.dorohedoro.common.EchoService");

        Method echo = ReflectionUtils.findMethod(clazz, "echo", String.class);
        log.info("{}", echo);

        ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                log.info("{}", method);
            }
        }, new ReflectionUtils.MethodFilter() {
            public boolean matches(Method method) {
                Class[] parameterClazz = method.getParameterTypes();
                Class[] exceptionClazz = method.getExceptionTypes();
                return parameterClazz.length == 1
                        && String.class.equals(parameterClazz[0])
                        && exceptionClazz.length == 1
                        && NullPointerException.class.equals(exceptionClazz[0]);
            }
        });
    }
}
