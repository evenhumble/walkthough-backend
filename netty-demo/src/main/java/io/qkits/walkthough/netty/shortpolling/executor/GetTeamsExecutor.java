package io.qkits.walkthough.netty.shortpolling.executor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.apache.http.client.methods.HttpGet;

import com.fasterxml.jackson.core.type.TypeReference;

import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;
import io.qkits.walkthough.netty.shortpolling.response.TeamEntry;
import io.qkits.walkthough.netty.shortpolling.response.TeamEntryArray;

public class GetTeamsExecutor extends BasicExecutor<TeamEntryArray, Set<String>> {
    public GetTeamsExecutor() {
        super(new HttpGet("/team"), new TypeReference<BasicResponse<TeamEntryArray>>() {
        }, true);
    }

    @Override
    protected Set<String> getResult(TeamEntryArray data) throws NettyWalkthroughException {
        TeamEntry[] teamArray = data.getTeams();
        if (teamArray == null) {
            throw new NettyWalkthroughException("Team array is null!");
        }
        return Stream.of(teamArray).map(entry -> entry.getName()).collect(Collectors.toSet());
    }
}
