package com.dorohedoro.intro;

import java.lang.reflect.Method;

public interface BeforeInterceptor {
    Object before(Object proxy, Method method, Object[] args);
}
