package com.dorohedoro.common;

import java.lang.reflect.Method;

public interface AfterReturnInterceptor {
    Object afterReturn(Object proxy, Method method, Object[] args, Object res);
}
