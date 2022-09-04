package com.dorohedoro.intro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

@Slf4j
public class EchoServiceFactoryBean implements FactoryBean<EchoService>  {
    @Override
    public EchoService getObject() throws Exception {
        return (EchoService) Proxy.newProxyInstance(EchoService.class.getClassLoader(), new Class[]{EchoService.class}, ((proxy, method, args) -> {
            log.info("before");
            try {
                EchoService target = new DefaultEchoService();
                return method.invoke(target, args);
            } finally {
                log.info("after"); 
            }
        }));
    }

    @Override
    public Class<?> getObjectType() {
        return EchoService.class;
    }
}
