package io.qkits.walkthough.netty.modules.netty.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1. author: patrick
 */
public class BlockingIOExample {
    private static  final int PORT_NUMBER=9090;
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT_NUMBER);
        Socket socket = ss.accept(); // this is blocking
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        String request,response;
        while((request=in.readLine())!=null){
            if("Done".equalsIgnoreCase(request)){
                break;
            }
        }
        response= request+" Processed Request";
        out.println(response);

    }
}
