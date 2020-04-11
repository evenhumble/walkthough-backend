package io.qkits.benchmarks.db.controller;

import io.qkits.benchmarks.db.core.BenchmarkInMemResultStore;
import io.qkits.benchmarks.db.core.BenchmarkRunner;
import io.qkits.benchmarks.db.core.BenchmarkTest;
import io.qkits.benchmarks.db.core.DaoBenchmarkTestBuilder;
import io.qkits.benchmarks.db.jpa.JpaDaoBenchmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * QMETA
 * created at: 2020/4/11
 * created by: patrick
 **/
@RestController
public class BenchmarkController {
    @Autowired
    BenchmarkRunner runner;

    @Autowired
    BenchmarkInMemResultStore resultStore;

    @Autowired
    JpaDaoBenchmarkService jpaService;

    @GetMapping("/benchmark/{typeName}")
    public ResponseEntity<String> runTest(@PathVariable String typeName) {

        BenchmarkTest test = DaoBenchmarkTestBuilder.build(typeName,jpaService);
        runner.runBenchmark(test);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/benchmarkresult/{typeName}")
    public ResponseEntity getResult(@PathVariable String typeName) {
        return ResponseEntity.ok(resultStore.getAndWriteResultByCategoryName(typeName));
    }
}
