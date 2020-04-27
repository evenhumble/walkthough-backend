package io.qkits.corejava.corejava.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Sometimes if you can put the file content to memory, then the process procedure is more effective.
 * User: mazhqa
 * Date: 14-4-28
 */
public class MapFileToMemory {

    public static void main(String[] args) {
        //in idea 11.1.5, the java7 source sugar is not supported(try-with-resources)
        FileChannel channel = null;
        try {
            channel = FileChannel.open(Paths.get("a.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE);
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
            byte b = byteBuffer.get(1024);
            byteBuffer.put(1025, b);
            byteBuffer.force();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {

                }
            }
        }

    }
}
