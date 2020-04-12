package io.qkits.benchmarks.db.controller;

import io.qkits.benchmarks.db.core.*;
import io.qkits.benchmarks.db.core.store.BenchmarkInMemResultStore;
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


    @GetMapping("/benchmark/{typeName}")
    public ResponseEntity<String> runTest(@PathVariable String typeName) {
        runner.runBenchmarkByType(typeName);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/benchmarkresult/{typeName}")
    public ResponseEntity getResult(@PathVariable String typeName) {
        return ResponseEntity.ok(resultStore.writeCategoryResult(typeName));
    }
}
