package io.qkits.corejava.corejava.nio.reactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author mazhiqiang
 */
public class ServerMain {

    public static void main(String[] args) {

        try {
            DemultiPlexer demultiPlexer = new DemultiPlexer();
            ServerSocket serverSocket = new ServerSocket(8888);
            Runtime.getRuntime().addShutdownHook(new ServerShutdownHookThread(serverSocket));
            while (true) {
                Socket socket = serverSocket.accept();
                int requestId = socket.getInputStream().read();
                if (requestId != -1) {
                    demultiPlexer.accept(requestId);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
