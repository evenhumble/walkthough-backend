package io.qkits.corejava.corejava.guavasamples.collections;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collections;

import static io.allroundtester.walkthrough.corejava.guavasamples.SampleUtil.*;

public class GuavaMultiMap {
    public static void main(String[] args) {
        Multimap<String, Integer> scores = HashMultimap.create();
        scores.put("Josh", 20);
        scores.put("Josh", 10);
        scores.put("Josh", 15);
        scores.put("Krisna", 10);
        scores.put("Krisna", 30);

        printTitle("Google Guava : MultiMap ");
        println("All Scores : " + scores.toString());
        println("Josh Scores : " + scores.get("Josh"));
        println("Max Josh Score : " + Collections.max(scores.get("Josh")));
        println("Krisna Scores : " + scores.get("Krisna"));
        println("Max Krisna Score : " + Collections.max(scores.get("Krisna")));

    }
}
