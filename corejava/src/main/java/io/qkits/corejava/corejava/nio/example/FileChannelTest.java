package io.qkits.corejava.corejava.nio.example;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * User: mazhqa
 * Date: 13-11-21
 */
public class FileChannelTest {

    public static void main(String[] args){
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            File file = new File("D:\\Code\\Github\\java-learn\\nio\\src\\com\\clamaa\\nio\\FileChannelTest.java");
            inChannel = new FileInputStream(file).getChannel();
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            Charset charset = Charset.forName("GBK");
            outChannel = new FileOutputStream("d:/a.txt").getChannel();
            outChannel.write(buffer);
            buffer.clear();
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer);
            System.out.println(charBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                inChannel.close();
                outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
