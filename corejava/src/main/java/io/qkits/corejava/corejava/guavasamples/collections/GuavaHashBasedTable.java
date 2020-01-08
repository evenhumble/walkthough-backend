package io.qkits.corejava.corejava.guavasamples.collections;

import static io.allroundtester.walkthrough.corejava.guavasamples.SampleUtil.*;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class GuavaHashBasedTable {
    /**
     * Table Data structure
     * @param args
     */
    public static void main(String[] args) {
        Table<Long,String,Object> hashBaseTables= HashBasedTable.create();

        //Row One
        hashBaseTables.put(1l,"First Name",new String("Krisna"));
        hashBaseTables.put(1l,"Last Name",new String("Putra"));
        hashBaseTables.put(1l,"Age",new Integer(28));
        //Row Two
        hashBaseTables.put(2l,"First Name",new String("Dira"));
        hashBaseTables.put(2l,"Last Name",new String("Safitri"));
        hashBaseTables.put(2l,"Age",new Integer(25));

        //Row Three
        hashBaseTables.put(3l,"First Name",new String("Nagita"));
        hashBaseTables.put(3l,"Last Name",new String("Wishly"));
        hashBaseTables.put(3l,"Age",new Integer(1));

        printTitle("Google Guava : HashBaseTable");
        println("Select * from Student Table : "+hashBaseTables);
        println("Select * from Student Table Where Row=1 -->"+hashBaseTables.row(1l));
        println("Select First Name from Student Table where Row=2 -->"+hashBaseTables.get(2l,"First Name"));
        println("Select * from Student Table Where Row=3 -->"+hashBaseTables.row(3l));

    }

}
