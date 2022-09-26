package com.dorohedoro.common;

import java.lang.reflect.Method;

public interface BeforeInterceptor {
    Object before(Object proxy, Method method, Object[] args);
}
