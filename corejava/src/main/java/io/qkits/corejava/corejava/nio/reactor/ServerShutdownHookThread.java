package io.qkits.corejava.corejava.nio.reactor;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author mazhiqiang
 */
public class ServerShutdownHookThread extends Thread {

    private ServerSocket serverSocket;

    public ServerShutdownHookThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
