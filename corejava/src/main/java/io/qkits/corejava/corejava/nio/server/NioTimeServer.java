package io.qkits.corejava.corejava.nio.server;

/**
 * @author mazhiqiang
 */
public class NioTimeServer {

    public static final int PORT = 8888;

    public static void main(String[] args) {
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(PORT);
        new Thread(timeServer, "NIO-MultiServer-001").start();
    }

}
