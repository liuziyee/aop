package com.dorohedoro.spring.aop.pointcut;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ClassAndMethodFilterPointcut extends StaticMethodMatcherPointcut {
    
    private String targetMethod;

    private Class targetClass;
    
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return Objects.equals(targetMethod, method.getName()) 
                && this.targetClass.isAssignableFrom(targetClass);
    }
    
}
