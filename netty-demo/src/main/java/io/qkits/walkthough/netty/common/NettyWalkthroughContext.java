package io.qkits.walkthough.netty.common;

import lombok.Getter;
import lombok.Setter;

public class NettyWalkthroughContext {
    @Setter
    private static String url;
    @Getter
    @Setter
    private static String token;

    public static String getUrl() {
        return String.format("http://%s/api", url);
    }

    public static boolean isLocalized() {
        return url == null || url.trim().isEmpty();
    }
}
