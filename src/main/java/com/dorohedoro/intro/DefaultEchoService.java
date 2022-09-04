package com.dorohedoro.intro;

public class DefaultEchoService implements EchoService{
    public String echo(String msg) throws NullPointerException {
        return "[ECHO] " + msg;
    }
}
