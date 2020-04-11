package io.qkits.benchmarks.db.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
@Service
@Slf4j
@Async
public class BenchmarkRunner {

    @Autowired
    private BenchmarkInMemResultStore resultStore;

    private void warmUp(BenchmarkTest benchmarkTest) {
        for (BenchmarkTask task : benchmarkTest.getTasks()) {
            for (int i = 0; i < benchmarkTest.getWarmCount(); i++) {
                task.getTask().run();
            }
        }
    }

    public void runBenchmark(BenchmarkTest benchmarkTest) {
        warmUp(benchmarkTest);
        for (BenchmarkTask task : benchmarkTest.getTasks()) {
            BenchmarkTaskResult result = startBenchmarkTask(benchmarkTest, task);
            for (int i = 0; i < benchmarkTest.getTestCount(); i++) {
                log.info("run testing {},loop {}",benchmarkTest.getName(),i);
                task.getTask().run();
            }
            endBenchmarkTask(result);
            resultStore.addBenchmarkResult(benchmarkTest.getName(), result);
        }
        resultStore.writeOverallBenchmarkResult();
    }


    private BenchmarkTaskResult endBenchmarkTask(BenchmarkTaskResult result) {
        result.setEndTime(System.currentTimeMillis());
        return result;
    }

    private BenchmarkTaskResult startBenchmarkTask(BenchmarkTest test, BenchmarkTask task) {
        return BenchmarkTaskResult.builder()
                .categoryName(test.getName())
                .taskName(task.getName().name())
                .count(test.getTestCount())
                .startTime(System.currentTimeMillis()).build();
    }
}
