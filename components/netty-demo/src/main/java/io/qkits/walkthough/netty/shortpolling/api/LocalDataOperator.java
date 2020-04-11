package io.qkits.walkthough.netty.shortpolling.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.qkits.walkthough.netty.common.DataEntry;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;


public class LocalDataOperator implements DataOperator {
    @Override
    public void update(String cluster, String key, String value, String runtime) throws NettyWalkthroughException {
    }

    @Override
    public void delete(String cluster, String key) throws NettyWalkthroughException {
    }

    @Override
    public DataEntry get(String cluster, String key) throws NettyWalkthroughException {
        return new DataEntry();
    }

    @Override
    public Map<String, DataEntry> getMap(String cluster) throws NettyWalkthroughException {
        return new HashMap<>();
    }

    @Override
    public List<String> getClusters() throws NettyWalkthroughException {
        return new ArrayList<>();
    }
}
