package io.qkits.corejava.corejava.guavasamples.base;

import static io.allroundtester.walkthrough.corejava.guavasamples.SampleUtil.*;

import com.google.common.base.CharMatcher;


public class GuavaCharMatcher {

  public static void main(String[] args) {
    printTitle("Google Guava ChatMatcher");

    printlnSubTitle("Google Guava : Get Only DIGIT Or Letter Only");
    String data = "123123This Is Letter$%^&*()_12312";
    printlnTab("Data : " + data);
    printlnTab("Get Digit From Data  : " + CharMatcher.digit().retainFrom(data));
    printlnTab("Get Letter From Data : " + CharMatcher.javaLetter().or(CharMatcher.whitespace())
        .retainFrom(data));
    endPrintTab();

    printlnSubTitle("Google Guava : Remove All Upper Case and Lower Case");
    data = "THIS IS UPPER CASE story of my code by www.putracode.com";
    printlnTab("Data : " + data);
    printlnTab("Remove All Upper Case : " + CharMatcher.javaLowerCase().removeFrom(data));
    printlnTab("Remove All Lower Case : " + CharMatcher.javaLowerCase().removeFrom(data));
    endPrintTab();

    printlnSubTitle("Google Guava : Count Matching Char");
    data = "www.putracode.com awesome 123465";
    printlnTab("Data : " + data);
    printlnTab("Count Matching Char Digit :" + CharMatcher.digit().countIn(data));
    printlnTab("Count Matching Char \"w\" : " + CharMatcher.javaLetter().is('w').countIn(data));
    endPrintTab();

    printlnSubTitle("Google Guava : Range Checker");
    printlnTab("Range \'A\' to \'J\' for Char \'X\'" + CharMatcher.inRange('A', 'J').apply('X'));
    printlnTab("Range \'A\' to \'J\' for Char \'E\'" + CharMatcher.inRange('A', 'J').apply('E'));
    endPrintTab();

    printlnSubTitle("Google Guava : Remove String \'#\'");
    data = "www.putr#acode.com awe#some 123#465";
    printlnTab("Data : " + data);
    printlnTab("Remove Char \'#\' : " + CharMatcher.is('#').removeFrom(data));
    endPrintTab();

  }
}
