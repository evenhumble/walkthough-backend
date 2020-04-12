package io.qkits.benchmarks.db.core.store;

import io.qkits.benchmarks.db.core.BenchmarkTaskResult;

import java.util.List;
import java.util.Map;

public interface BenchmarkResultStore {
    List<BenchmarkTaskResult> getResultByCategoryName(String categoryName);

    Map<String, List<BenchmarkTaskResult>> getTestResults();

    void writeOverallBenchmarkResult();

    void addBenchmarkResult(String categoryName,
                            BenchmarkTaskResult result);

    List<BenchmarkTaskResult> writeCategoryResult(String categoryName);
}
