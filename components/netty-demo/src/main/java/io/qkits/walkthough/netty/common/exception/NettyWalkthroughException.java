package io.qkits.walkthough.netty.common.exception;

@SuppressWarnings("serial")
public class NettyWalkthroughException extends Exception {
    public NettyWalkthroughException() {
        super();
    }

    public NettyWalkthroughException(String message) {
        super(message);
    }

    public NettyWalkthroughException(Throwable cause) {
        super(cause);
    }

    public NettyWalkthroughException(String message, Throwable cause) {
        super(message, cause);
    }
}
