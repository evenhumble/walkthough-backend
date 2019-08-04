package io.qkits.walkthough.netty.shortpolling.api;

import java.util.Set;

import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.executor.GetAppsExecutor;
import io.qkits.walkthough.netty.shortpolling.executor.GetTeamsExecutor;


public class RemoteGlobalOperator implements GlobalOperator {

  @Override
  public Set<String> getTeams() throws NettyWalkthroughException {
    return new GetTeamsExecutor().execute();
  }

  @Override
  public Set<String> getAppsByTeam(String team) throws NettyWalkthroughException {
    return new GetAppsExecutor(team).execute();
  }
}
