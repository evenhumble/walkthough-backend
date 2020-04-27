package io.qkits.corejava.corejava.nio.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: mazhqa
 * Date: 13-11-21
 */
public class RandomFileChannelTest {

    public static void main(String[] args){
        FileChannel randomChannel = null;
        try {
            File file = new File("d:/a.txt");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomChannel = randomAccessFile.getChannel();
            ByteBuffer byteBuffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            randomChannel.position(file.length());
            randomChannel.write(byteBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if(randomChannel != null){
                try {
                    randomChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

}
