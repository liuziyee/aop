package com.dorohedoro.intro;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class AopInterceptorDemo {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "false");
        
        Object proxy = Proxy.newProxyInstance(EchoService.class.getClassLoader(), new Class[]{EchoService.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    Long startTime = 0L;
                    Long endTime = 0L;
                    Object res = null;
                    try {
                        BeforeInterceptor beforeInterceptor = new BeforeInterceptor() {
                            public Object before(Object proxy, Method method, Object[] args) {
                                return System.currentTimeMillis();
                            }
                        };
                        startTime = (Long) beforeInterceptor.before(proxy, method, args);
                        log.info("before timestamp: {}", startTime);

                        DefaultEchoService target = new DefaultEchoService();
                        res = method.invoke(target, args[0]);
                        log.info("echo(): {}", res);

                        AfterReturnInterceptor afterReturnInterceptor = new AfterReturnInterceptor() {
                            public Object afterReturn(Object proxy, Method method, Object[] args, Object res) {
                                return System.currentTimeMillis();
                            }
                        };
                        endTime = (Long) afterReturnInterceptor.afterReturn(proxy, method, args, res);
                        log.info("after return timestamp: {}", endTime);
                    } catch (Exception e) {
                        AfterThrowInterceptor afterThrowInterceptor = new AfterThrowInterceptor() {
                            public Object afterThrow(Object proxy, Method method, Object[] args, Throwable throwable) {
                                log.info("{}, {}", throwable.getMessage(), throwable);
                                return throwable;
                            }
                        };
                        afterThrowInterceptor.afterThrow(proxy, method, args, e);
                    } finally {
                        FinallyInterceptor finallyInterceptor = new TimeFinallyInterceptor(startTime, endTime);
                        Object time = finallyInterceptor.finalize(proxy, method, args, res);
                        log.info("finally echo() takes " + time + " ms");
                    }
                }
                return null;
            }
        });
        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello,World");
    }
}

class TimeFinallyInterceptor implements FinallyInterceptor {
    private final Long startTime;

    private final Long endTime;

    public TimeFinallyInterceptor(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Object finalize(Object proxy, Method method, Object[] args, Object res) {
        return endTime - startTime;
    }
}