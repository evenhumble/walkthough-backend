package io.qkits.corejava.corejava.guavasamples;

/**
 * Created by patrick on 16/6/23.
 */
public class SampleUtil {
    public static final String HEADER_PRINT="*****************";
    private SampleUtil(){}

    public static void println(String s){
        System.out.println(s);
    }

    public static void printTitle(String s){
        println(HEADER_PRINT);
        println(s);
        println(HEADER_PRINT);

    }
    public static void printlnSubTitle(String s){
        printlnTab(">>>>"+s);
    }
    public static void printlnTab(String s){
        println("\t"+s);
    }
    public static void endPrintTab(){
        printlnTab("<<<<<");
    }

}
