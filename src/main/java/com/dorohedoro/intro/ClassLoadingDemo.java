package com.dorohedoro.intro;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassLoadingDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        log.info("{}", classLoader.getClass());

        while (true) {
            classLoader = classLoader.getParent();
            if (classLoader == null) break;
            log.info("{}", classLoader.getClass());
        }
    }
}
