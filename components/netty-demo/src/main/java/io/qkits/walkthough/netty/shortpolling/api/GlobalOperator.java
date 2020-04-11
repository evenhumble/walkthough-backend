package io.qkits.walkthough.netty.shortpolling.api;

import java.util.HashSet;
import java.util.Set;

import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;


public interface GlobalOperator {
    Set<String> getTeams() throws NettyWalkthroughException;

    Set<String> getAppsByTeam(String team) throws NettyWalkthroughException;

    default Set<String> getApps() throws NettyWalkthroughException {
        Set<String> apps = new HashSet<>();
        for (String team : getTeams()) {
            apps.addAll(getAppsByTeam(team));
        }
        return apps;
    }
}
