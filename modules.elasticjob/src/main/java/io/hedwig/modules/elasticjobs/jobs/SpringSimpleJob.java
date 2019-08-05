package io.hedwig.modules.elasticjobs.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import io.hedwig.modules.elasticjobs.domain.entity.Foo;
import io.hedwig.modules.elasticjobs.domain.repository.FooRepository;

public class SpringSimpleJob implements SimpleJob {

    @Resource
    private FooRepository fooRepository;


    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format("Item: %s | Time: %s | Thread: %s | %s",
                                         shardingContext.getShardingItem(),
                                         new SimpleDateFormat("HH:mm:ss").format(new Date()),
                                         Thread.currentThread().getId(), "SIMPLE"));
        List<Foo> data = fooRepository.findTodoData(shardingContext.getShardingParameter(), 10);
        for (Foo each : data) {
            fooRepository.setCompleted(each.getId());
        }
    }
}
