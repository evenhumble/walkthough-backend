package io.hedwig.modules.elasticjobs.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.hedwig.modules.elasticjobs.domain.entity.Foo;
import io.hedwig.modules.elasticjobs.domain.repository.FooRepository;
import io.hedwig.modules.elasticjobs.domain.repository.FooRepositoryFactory;

public class JavaDataFlowJob implements DataflowJob<Foo> {

    private FooRepository fooRepository = FooRepositoryFactory.getFooRepository();

    @Override
    public List<Foo> fetchData(ShardingContext shardingContext) {
        System.out.println(String.format("Item: %s | Time: %s | Thread: %s | %s",
                                         shardingContext.getShardingItem(),
                                         new SimpleDateFormat("HH:mm:ss").format(new Date()),
                                         Thread.currentThread().getId(), "DATAFLOW FETCH"));
        return fooRepository.findTodoData(shardingContext.getShardingParameter(), 10);
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Foo> data) {
        System.out.println(String.format("Item: %s | Time: %s | Thread: %s | %s",
                                         shardingContext.getShardingItem(),
                                         new SimpleDateFormat("HH:mm:ss").format(new Date()),
                                         Thread.currentThread().getId(), "DATAFLOW PROCESS"));
        for (Foo each : data) {
            fooRepository.setCompleted(each.getId());
        }
    }
}
