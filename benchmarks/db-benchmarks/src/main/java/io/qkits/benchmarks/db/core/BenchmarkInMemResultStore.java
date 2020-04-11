package io.qkits.benchmarks.db.core;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
@Component
public class BenchmarkInMemResultStore {

    @Value("${benchmark.resultDir}")
    private String resultDir;

    private Map<String, List<BenchmarkTaskResult>> benchmarkResult = new ConcurrentHashMap<>();

    public void addBenchmarkResult(String categoryName,
                                   BenchmarkTaskResult result) {
        List<BenchmarkTaskResult> current =
                benchmarkResult.getOrDefault(categoryName, new LinkedList<>());
        current.add(result);
        benchmarkResult.put(categoryName,current);
    }

    public void writeOverallBenchmarkResult() {
        for (Map.Entry<String, List<BenchmarkTaskResult>> entry : benchmarkResult.entrySet()) {
            String fileName = entry.getKey() + ".csv";
            File file = new File(resultDir, fileName);
            StringBuilder testResultContent = new StringBuilder();
            for (BenchmarkTaskResult benchmarkTaskResult : entry.getValue()) {
                String line = benchmarkTaskResult.toCsv() + "\n";
                testResultContent.append(line);
            }
            FileUtil.writeAsString(file, testResultContent.toString());
        }
    }

    public List<BenchmarkTaskResult> getAndWriteResultByCategoryName(String categoryName) {
        List<BenchmarkTaskResult> results = benchmarkResult.getOrDefault(categoryName, new LinkedList<>());
        String fileName = categoryName + ".csv";
        File file = new File(resultDir, fileName);
        StringBuilder testResultContent = new StringBuilder();
        for (BenchmarkTaskResult benchmarkTaskResult : results) {
            String line = benchmarkTaskResult.toCsv() + "\n";
            testResultContent.append(line);
        }
        FileUtil.writeAsString(file, testResultContent.toString());
        return results;
    }
}
