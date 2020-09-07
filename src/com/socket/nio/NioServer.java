package com.socket.nio;

public class NioServer  {
    public static void main(String[] args) {
        MultiplexerNioServer multiplexerNioServer=new MultiplexerNioServer(8080);

        new Thread(multiplexerNioServer,"nioserver-001").start();
    }
}
