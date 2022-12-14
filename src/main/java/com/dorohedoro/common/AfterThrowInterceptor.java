package com.dorohedoro.common;

import java.lang.reflect.Method;

public interface AfterThrowInterceptor {
    Object afterThrow(Object proxy, Method method, Object[] args, Throwable throwable);
}
