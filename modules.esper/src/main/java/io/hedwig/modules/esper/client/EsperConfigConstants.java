package io.hedwig.modules.esper.client;

/**
 * 1. author: patrick
 */
public class EsperConfigConstants {
    public static final int MINIMUM_RATE = 1000;
    public static final int DEFAULT_PORT = 6789;
    public static final int DEFAULT_RATE = 4000;
    public static final String DEFAULT_HOST = "localhost";

    private static void printUsage() {
        System.err.println("com.espertech.esper.example.benchmark.client.Client <-host hostname> <-port #> <-rate #>");
        System.err.println("defaults:");
        System.err.println("    Rate: " + DEFAULT_RATE + " msg/s");
        System.err.println("    Host: " + DEFAULT_HOST);
        System.err.println("    Port: " + DEFAULT_PORT);
        System.exit(1);
    }
}
