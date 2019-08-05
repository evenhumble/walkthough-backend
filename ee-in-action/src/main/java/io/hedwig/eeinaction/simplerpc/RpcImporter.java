package io.hedwig.eeinaction.simplerpc;

import static java.lang.reflect.Proxy.newProxyInstance;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcImporter<S> {

    public S importer(final Class<?> serviceClass, final InetSocketAddress address) {
        return (S) newProxyInstance(serviceClass.getClassLoader(),
                                    new Class<?>[]{serviceClass.getInterfaces()[0]},
                                    (proxy, method, args) -> {
                                        Socket socket = null;
                                        ObjectOutputStream output = null;
                                        ObjectInputStream input = null;

                                        try {
                                            // 连接远程服务提供者
                                            socket = new Socket();
                                            socket.connect(address);
                                            // 对象输出流
                                            output =
                                                new ObjectOutputStream(socket.getOutputStream());
                                            output.writeUTF(serviceClass.getName());
                                            output.writeUTF(method.getName());
                                            output.writeObject(method.getParameterTypes());
                                            output.writeObject(args);
                                            input = new ObjectInputStream(socket.getInputStream());
                                            System.out.println("This is echo service importer");
                                            return input.readObject();
                                        } finally {
                                            if (socket != null) {
                                                socket.close();
                                            }
                                            if (output != null) {
                                                output.close();
                                            }
                                            if (input != null) {
                                                input.close();
                                            }
                                        }
                                    });
    }
}
