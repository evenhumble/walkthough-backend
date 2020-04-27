package io.qkits.corejava.corejava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * User: mazhqa
 * Date: 14-4-28
 */
public class SelectorExample {

    public static void main(String[] args) {
        Selector selector = null;
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_READ);
            while (true) {
                int readyChannels = selector.select();
                if (readyChannels == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (java.util.Iterator<SelectionKey> iterator = selectionKeys.iterator(); iterator.hasNext(); ) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {

                    }
                    if (key.isConnectable()) {

                    }
                    if (key.isReadable()) {

                    }
                    if (key.isWritable()) {

                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {

                }
            }
        }

    }
}
