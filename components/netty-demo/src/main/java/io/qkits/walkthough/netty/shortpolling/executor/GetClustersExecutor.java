package io.qkits.walkthough.netty.shortpolling.executor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.apache.http.client.methods.HttpGet;

import com.fasterxml.jackson.core.type.TypeReference;

import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorContext;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;
import io.qkits.walkthough.netty.shortpolling.response.ClusterEntry;

public class GetClustersExecutor extends AppExecutor<ClusterEntry[], List<String>> {
    public GetClustersExecutor(DataOperatorContext dataContext) {
        super(new HttpGet(""), new TypeReference<BasicResponse<ClusterEntry[]>>() {
        }, true, dataContext);
    }

    @Override
    protected List<String> getResult(ClusterEntry[] data) throws NettyWalkthroughException {
        return Stream.of(data).map((entry) -> entry.getName()).collect(Collectors.toList());
    }
}
