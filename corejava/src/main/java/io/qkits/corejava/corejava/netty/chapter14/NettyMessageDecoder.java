package io.qkits.corejava.corejava.netty.chapter14;

import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 * @author mazhiqiang
 */
public class NettyMessageDecoder extends MarshallingDecoder {

    public NettyMessageDecoder(UnmarshallerProvider provider) {
        super(provider);
    }


}
