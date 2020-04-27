package io.qkits.corejava.corejava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * User: mazhqa
 * Date: 14-4-28
 */
public class LoadWebPageUseSelector {

    private void register(Selector selector, SocketAddress socketAddress) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(socketAddress);
        channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
    }

    private Map<SocketAddress, String> urlToSocketAddress(Set<URL> urls) {
        Map<SocketAddress, String> mappings = new HashMap<SocketAddress, String>();
        for (URL url : urls) {
            int port = url.getPort() != -1 ? url.getPort() : url.getDefaultPort();
            SocketAddress socketAddress = new InetSocketAddress(url.getHost(), port);
            String path = url.getPath();
            if (url.getQuery() != null) {
                path += "?" + url.getQuery();
            }
            mappings.put(socketAddress, path);
        }
        return mappings;
    }

    public void load(Set<URL> urls) throws IOException {
        Map<SocketAddress, String> addressStringMap = urlToSocketAddress(urls);
        Selector selector = Selector.open();
        for (SocketAddress socketAddress : addressStringMap.keySet()) {
            register(selector, socketAddress);
        }
        int finished = 0;
        int total = addressStringMap.size();
        ByteBuffer byteBuffer = ByteBuffer.allocate(32 * 1024);
        int len = -1;
        while (finished < total) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isValid() && selectionKey.isReadable()) {
                    SocketChannel selectableChannel = (SocketChannel) selectionKey.channel();
                    InetSocketAddress remoteAddress = (InetSocketAddress) selectableChannel.getRemoteAddress();
                    String fileName = remoteAddress.getHostName() + ".txt";
                    FileChannel fileChannel = FileChannel.open(Paths.get(fileName), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    byteBuffer.clear();
                    while ((len = selectableChannel.read(byteBuffer)) > 0 || byteBuffer.position() != 0) {
                        byteBuffer.flip();
                        fileChannel.write(byteBuffer);
                        byteBuffer.compact();
                    }
                    if (len == -1) {
                        finished++;
                        selectionKey.cancel();
                    }

                } else if (selectionKey.isValid() && selectionKey.isConnectable()) {
                    SocketChannel selectableChannel = (SocketChannel) selectionKey.channel();
                    boolean success = selectableChannel.finishConnect();
                    if (!success) {
                        finished++;
                        selectionKey.cancel();
                    } else {
                        InetSocketAddress address = (InetSocketAddress)selectableChannel.getRemoteAddress();
                        String path = addressStringMap.get(address);
                        String request = String.format("GET %s HTTP/1.0\r\n\r\nHost:%s\r\n\r\n", path, address.getHostString());
                        ByteBuffer header = ByteBuffer.wrap(request.getBytes("UTF-8"));
                        selectableChannel.write(header);
                    }

                }
            }
        }
    }

}
