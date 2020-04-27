package io.qkits.corejava.corejava.netty.chapter13;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author mazhiqiang
 */
public class FileServerHandler extends SimpleChannelInboundHandler<String> {

    public static final String CR = System.getProperty("line.separator");

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String filePath) throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            if (!file.isFile()) {
                ctx.writeAndFlush("The given path is not file: " + filePath);
                return;
            }
            ctx.write(String.format("%s  %s%s", file, file.length(), CR));
            RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
            FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(), 0, randomAccessFile.length());
            ctx.write(region);
            ctx.writeAndFlush(CR);
            randomAccessFile.close();
        } else {
            ctx.writeAndFlush("File Not Found: " + filePath);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
