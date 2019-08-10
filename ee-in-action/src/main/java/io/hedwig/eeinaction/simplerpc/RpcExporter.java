package io.hedwig.eeinaction.simplerpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RpcExporter {
    static Executor executor = Executors
        .newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void exporter(String hostName,int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(hostName, port));
        try {
            while (true) {
                /**
                 * 监听Client的TCP连接,将其封装成Task,由线程池执行.
                 */
                executor.execute(new ExporterTask(serverSocket.accept()));
            }
        } finally {
            System.out.println("shutdown server.......");
            serverSocket.close();
        }
    }

    private static class ExporterTask implements Runnable {
        Socket client = null;
        public ExporterTask(Socket accept) {
            this.client = accept;
        }

        @Override
        public void run() {
            ObjectInputStream   input  = null;
            ObjectOutputStream  output = null;
            try {
                // 对象输入流
                input = new ObjectInputStream(client.getInputStream());
                // 获取接口名
                String interfaceName = input.readUTF();
                // 获取方法名
                String methodName = input.readUTF();
                // 获取方法的参数数组
                Class<?>[] paramTypes = (Class<?>[]) input.readObject();
                // 获取传入参数对象数组
                Object[] arguments = (Object[]) input.readObject();

                // 获取服务对象类
                Class<?> service = Class.forName(interfaceName);
                // 获取服务方法
                Method method = service.getMethod(methodName, paramTypes);
                // 获取服务方法返回对象
                Object result = method.invoke(service.newInstance(),arguments);

                // 对象输出流
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 关闭流的操作
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
