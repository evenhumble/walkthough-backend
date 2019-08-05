package io.hedwig.modules.netty.basic;

import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 1. author: patrick
 */
public class SocketSender {
    private static final String HOST="localhost";
    private static final int PORT=9090;
    public static void main(String[] args) throws IOException {
        Socket socket  = new Socket(HOST,PORT);

        while(true){
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            InputStream is =socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info="this is request";
            pw.write(info);
            pw.flush();
//            socket.shutdownInput();
            String response=null;

            while(!((response=br.readLine())==null)){
                System.out.println("接收服务器的信息："+response);
            }
            //4.关闭资源


        }

    }
}
