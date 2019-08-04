package io.qkits.walkthough.netty.shortpolling.api;

import java.util.HashSet;
import java.util.Set;

import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;


public class LocalGlobalOperator implements GlobalOperator {
    @Override
    public Set<String> getTeams() throws NettyWalkthroughException {
        return new HashSet<>();
    }

    @Override
    public Set<String> getAppsByTeam(String team) throws NettyWalkthroughException {
        return new HashSet<>();
    }
}
