package io.qkits.corejava.corejava.guavasamples;

import com.google.common.collect.*;
import org.testng.annotations.Test;

public class NewCollections {

    @Test
    public void testMultiset() {
        Multiset<String> multiset = HashMultiset.create();

        multiset.add("a");
        multiset.add("b");
        multiset.add("a");

        System.out.println(multiset.count("a"));
    }

    @Test
    public void testSortedMultiset() {

    }

    @Test
    public void testMultimap() {
        Multimap<String,User> userMultimap = ArrayListMultimap.create();
        for (int i = 10; i < 20; i++){
            User user=new User("i = " + i, i);
            userMultimap.put("peida",user);
        }
        System.out.println("scoreMultimap:" + userMultimap.size());
        System.out.println("scoreMultimap:" + userMultimap.keys());
    }

    @Test
    public void testBitMap() {
        BiMap<Integer, String> biMap = HashBiMap.create();

        biMap.put(1, "a");
        biMap.put(2, "b");

        BiMap<String, Integer> inverse = biMap.inverse();
        System.out.println(inverse.get("a"));
    }

    @Test
    public void testTable() {
        Table<String, Integer, String> aTable = HashBasedTable.create();

        for (char a = 'A'; a <= 'C'; ++a) {
            for (Integer b = 1; b <= 3; ++b) {
                aTable.put(Character.toString(a), b, String.format("%c%d", a, b));
            }
        }

        /**
         * A1 A2 A3
         * B1 B2 B3
         * C1 C2 C3
         */

        System.out.println(aTable.column(2));
        System.out.println(aTable.row("B"));
        System.out.println(aTable.get("B", 2));

        System.out.println(aTable.contains("D", 1));
        System.out.println(aTable.containsColumn(3));
        System.out.println(aTable.containsRow("C"));
        System.out.println(aTable.columnMap());
        System.out.println(aTable.rowMap());

        System.out.println(aTable.remove("B", 3));
    }
}
