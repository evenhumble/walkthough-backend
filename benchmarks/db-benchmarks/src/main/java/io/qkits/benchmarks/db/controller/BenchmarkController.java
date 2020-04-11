package io.qkits.benchmarks.db.controller;

import io.qkits.benchmarks.db.core.*;
import io.qkits.benchmarks.db.jpa.JpaDaoBenchmarkService;
import io.qkits.benchmarks.db.mybatisplus.MybatisplusDaoBenchmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

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

    Map<String, DaoBenchmarkService> serviceMap =new HashMap<>();


    @Autowired
    JpaDaoBenchmarkService jpaService;
    @Autowired
    MybatisplusDaoBenchmarkService mybatisplusService;

    @PostConstruct
    public void initBenchmarkServices(){
        serviceMap.put("jpa",jpaService);
        serviceMap.put("mybatisplus",mybatisplusService);
    }

    @GetMapping("/benchmark/{typeName}")
    public ResponseEntity<String> runTest(@PathVariable String typeName) {
        DaoBenchmarkService service = this.serviceMap.get(typeName.toLowerCase());
        BenchmarkTest test = DaoBenchmarkTestBuilder.build(typeName,service);
        runner.runBenchmark(test);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/benchmarkresult/{typeName}")
    public ResponseEntity getResult(@PathVariable String typeName) {
        return ResponseEntity.ok(resultStore.getAndWriteResultByCategoryName(typeName));
    }
}
