package io.hedwig.tcexecutor.benchmark;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.hedwig.tcexecutor.benchmark.model.ApiDefinition;
import io.hedwig.tcexecutor.benchmark.model.JMeterAPITPContext;

@Fork(5)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Benchmark)
public class BaseBenchmark {

    protected Map<String, Object> getContext() {
        Map<String,Object> contextMap = new HashMap<>();
        ApiDefinition api = new ApiDefinition();
        api.setApiName("Baidu-API");
        api.setHttpMethod("GET");
        api.setUrl("/");
        JMeterAPITPContext context = new JMeterAPITPContext();
        context.setApi(api);
        context.setThreadNum(5);
        context.setDurationTime(10);
        contextMap.put("context",context);
        return contextMap;
    }

}