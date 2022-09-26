package com.dorohedoro.common;

import java.lang.reflect.Method;

public interface FinallyInterceptor {
    Object finalize(Object proxy, Method method, Object[] args, Object res);
}
