package io.qkits.walkthough.netty.shortpolling.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.qkits.walkthough.netty.common.DataEntry;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;


public interface DataOperator {
    void update(String cluster, String key, String value, String runtime) throws NettyWalkthroughException;

    void delete(String cluster, String key) throws NettyWalkthroughException;

    DataEntry get(String cluster, String key) throws NettyWalkthroughException;

    Map<String, DataEntry> getMap(String cluster) throws NettyWalkthroughException;

    List<String> getClusters() throws NettyWalkthroughException;

    default Map<String, Map<String, DataEntry>> getAll(String... clusters) throws NettyWalkthroughException {
        Map<String, Map<String, DataEntry>> all = new HashMap<>();
        for (String cluster : (clusters != null ? Arrays.asList(clusters) : getClusters())) {
            all.put(cluster, getMap(cluster));
        }
        return all;
    }
}
