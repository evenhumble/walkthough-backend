package io.qkits.corejava.corejava.netty.chapter14;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mazhiqiang
 */
public class NettyMarshallingDecoder extends LengthFieldBasedFrameDecoder {

    private NettyMarshallingDecoder marshallingDecoder;

    public NettyMarshallingDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        marshallingDecoder = new NettyMarshallingDecoder(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }


    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }

        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionID(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());

        int size = in.readInt();
        if (size > 0) {
            Map<String, Object> attachment = new HashMap<String, Object>();
            for (int i = 0; i < size; i++) {
                int keySize = in.readInt();
                byte[] keyArray = new byte[keySize];
                in.readBytes(keyArray);

            }
        }
        return null;
    }
}
