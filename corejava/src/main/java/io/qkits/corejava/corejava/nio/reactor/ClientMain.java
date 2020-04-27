package io.qkits.corejava.corejava.nio.reactor;

import java.io.IOException;
import java.net.Socket;

/**
 * @author mazhiqiang
 */
public class ClientMain extends Thread {

    private String address = "localhost";
    private int port = 8888;
    private int requestId;

    public ClientMain(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(address, port);
            socket.getOutputStream().write(requestId);
            sleep(5000);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new ClientMain(i).start();
        }
    }
}
