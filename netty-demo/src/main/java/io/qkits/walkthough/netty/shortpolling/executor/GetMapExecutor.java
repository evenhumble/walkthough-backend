package io.qkits.walkthough.netty.shortpolling.executor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.apache.http.client.methods.HttpGet;

import com.fasterxml.jackson.core.type.TypeReference;

import io.qkits.walkthough.netty.common.DataEntry;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorContext;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;
import io.qkits.walkthough.netty.shortpolling.response.FullDataEntry;

public class GetMapExecutor extends AppExecutor<FullDataEntry[], Map<String, DataEntry>> {
    public GetMapExecutor(String cluster, DataOperatorContext dataContext) {
        super(new HttpGet(String.format("/%s", cluster)), new TypeReference<BasicResponse<FullDataEntry[]>>() {
        }, true, dataContext);
    }

    @Override
    protected Map<String, DataEntry> getResult(FullDataEntry[] data) throws
                                                                     NettyWalkthroughException {
        return Stream.of(data).collect(Collectors.toMap(FullDataEntry::getKey, DataEntry::new));
    }
}
