package io.qkits.walkthough.netty.shortpolling.executor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.apache.http.client.methods.HttpGet;

import com.fasterxml.jackson.core.type.TypeReference;

import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.response.AppArray;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;

public class GetAppsExecutor extends BasicExecutor<AppArray, Set<String>> {
    public GetAppsExecutor(String team) {
        super(new HttpGet("/team/" + team), new TypeReference<BasicResponse<AppArray>>() {
        }, true);
    }

    @Override
    protected Set<String> getResult(AppArray data) throws NettyWalkthroughException {
        return Stream.of(data.getApplications()).collect(Collectors.toSet());
    }
}
