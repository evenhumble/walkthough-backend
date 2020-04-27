package io.qkits.corejava.corejava.nio.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * User: mazhqa
 * Date: 13-11-21
 */
public class ReadFileTest {

    public static void main(String[] args){
        FileChannel fileChannel = null;
        try{
//            InputStream is = ReadFileTest.class.getResourceAsStream("a.txt");
            FileInputStream fis = new FileInputStream("d:/a.txt");
            fileChannel = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(20);
            while (fileChannel.read(byteBuffer) != -1){
                byteBuffer.flip();
                Charset charset = Charset.forName("GBK");
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer charBuffer = decoder.decode(byteBuffer);
                System.out.println(charBuffer);
                byteBuffer.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileChannel != null){
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
