package com.dorohedoro.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyEchoService implements EchoService{
    private final EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    public String echo(String msg) throws NullPointerException {
        Long startTime = System.currentTimeMillis();
        try {
            return echoService.echo(msg);
        } finally {
            Long endTime = System.currentTimeMillis() - startTime;
            log.info("echo() takes " + endTime + " ms");
        }
    }
}
