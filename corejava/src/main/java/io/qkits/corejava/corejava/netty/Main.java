package io.qkits.corejava.corejava.netty;

/**
 * @author mazhiqiang
 */
public class Main {

    public static void main(String[] args) {
        int d = 5;
        System.out.println(String.format("%05d", d));
        d = 16;
        System.out.println(String.format("%05d", d));

        String message = "plt=0^c=2000022^v=1^ca^SEK=1002748";
        String[] split = message.split("\\^");
        for (String s : split) {
            System.out.println(s);
        }

    }

}
