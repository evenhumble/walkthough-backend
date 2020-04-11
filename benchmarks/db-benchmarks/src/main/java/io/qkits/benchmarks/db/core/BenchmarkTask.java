package io.qkits.benchmarks.db.core;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
@Data
@AllArgsConstructor
public class BenchmarkTask {

    private String categoryName;
    private BenchmarkTaskEnum name;
    private Runnable task;
}
