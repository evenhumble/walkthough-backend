package io.qkits.benchmarks.db.core;

import io.qkits.benchmarks.db.core.store.BenchmarkInMemResultStore;
import io.qkits.benchmarks.db.jpa.JpaDaoBenchmarkService;
import io.qkits.benchmarks.db.mybatisplus.MybatisplusDaoBenchmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
;import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 * TODO: multiple threads runner for performance benchmark
 **/
@Service
@Slf4j
@Async
public class BenchmarkRunner {

    @Autowired
    private BenchmarkInMemResultStore resultStore;
    Map<String, DaoBenchmarkService> serviceMap = new HashMap<>();
    @Autowired
    JpaDaoBenchmarkService jpaService;
    @Autowired
    MybatisplusDaoBenchmarkService mybatisplusService;

    @PostConstruct
    public void initBenchmarkServices() {
        serviceMap.put("jpa", jpaService);
        serviceMap.put("mybatisplus", mybatisplusService);
    }

    private void warmUp(BenchmarkTest benchmarkTest) {
        for (BenchmarkTask task : benchmarkTest.getTasks()) {
            for (int i = 0; i < benchmarkTest.getWarmCount(); i++) {
                task.getTask().run();
            }
        }
    }

    public void runBenchmarkByType(String typeName) {
        DaoBenchmarkService service = this.serviceMap.get(typeName.toLowerCase());
        BenchmarkTest test = DaoBenchmarkTestBuilder.build(typeName, service);
        runBenchmark(test);
    }

    public void runBenchmark(BenchmarkTest benchmarkTest) {
        warmUp(benchmarkTest);
        for (BenchmarkTask task : benchmarkTest.getTasks()) {
            BenchmarkTaskResult result = startBenchmarkTask(benchmarkTest, task);
            for (int i = 0; i < benchmarkTest.getTestCount(); i++) {
                log.info("run testing {},{},loop {}", benchmarkTest.getName(), task.getName(), i);
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
