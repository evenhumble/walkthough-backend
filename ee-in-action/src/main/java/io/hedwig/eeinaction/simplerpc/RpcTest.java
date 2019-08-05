package io.hedwig.eeinaction.simplerpc;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RpcTest {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                RpcExporter.exporter("localhost",9090);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        RpcImporter<EchoService> importer = new RpcImporter<>();
        EchoService echo = importer.importer(EchoServiceImpl.class,new InetSocketAddress("localhost",
                                                                                         9090));
        System.out.println(echo.echo("this is test"));
    }
}
