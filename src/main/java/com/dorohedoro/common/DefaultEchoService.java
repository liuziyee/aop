package com.dorohedoro.common;

public class DefaultEchoService implements EchoService{
    public String echo(String msg) throws NullPointerException {
        return "[ECHO] " + msg;
    }
}
