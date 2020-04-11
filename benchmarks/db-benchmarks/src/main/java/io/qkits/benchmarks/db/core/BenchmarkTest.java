package io.qkits.benchmarks.db.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
@Data
public class BenchmarkTest {
    private String name;
    private List<BenchmarkTask> tasks = new ArrayList<>();
    private int warmCount=20;
    private int testCount=10000;

    public void addTask(BenchmarkTask task){
        tasks.add(task);
    }
}
