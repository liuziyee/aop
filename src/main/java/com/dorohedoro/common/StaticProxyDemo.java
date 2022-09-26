package com.dorohedoro.common;

public class StaticProxyDemo {
    public static void main(String[] args) {
        ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello,World");
    }
}
