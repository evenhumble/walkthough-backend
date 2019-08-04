package io.qkits.walkthough.netty.longpolling.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.qkits.walkthough.netty.common.DataEntry;
import io.qkits.walkthough.netty.common.DataType;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.longpolling.response.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataFetcherContext {
    @NonNull
    private final DataType dataType;
    private final Map<String, Map<String, Map<DataEventRegistry, Object>>> registryMap = new ConcurrentHashMap<>();

    public void register(DataEventRegistry registry) {
        registryMap.putIfAbsent(registry.getService(), new ConcurrentHashMap<>());
        Map<String, Map<DataEventRegistry, Object>> clusterMap = registryMap.get(registry.getService());
        clusterMap.putIfAbsent(registry.getCluster(), new ConcurrentHashMap<>());
        clusterMap.get(registry.getCluster()).put(registry, new Object());
    }

    @SuppressWarnings("serial")
    public Map<String, Map<String, List<String>>> toRequest() {
        Map<String, Map<String, List<String>>> request = new HashMap<>();
        request.put(dataType.name().toLowerCase(), new HashMap<String, List<String>>() {
            {
                registryMap.forEach((key, value) -> put(key, new ArrayList<String>(value.keySet())));
            }
        });
        return request;
    }

    public void handleMessage(Response response) throws NettyWalkthroughException {
        DataEventType type = getDataEventType(response);
        if (type != null) {
            notify(type, getDatas(response));
        }
    }

    private DataEventType getDataEventType(Response response) throws NettyWalkthroughException {
        String message = response.getMessage();
        if (message == null) {
            throw new NettyWalkthroughException("Response message is null!");
        }

        DataEventType type = null;
        switch (message) {
        case "all":
            type = DataEventType.ALL;
            break;
        case "update":
            type = DataEventType.UPDATE;
            break;
        case "delete":
            type = DataEventType.DELETE;
            break;
        case "ping":
        default:
            break;
        }
        return type;
    }

    private Map<String, Map<String, Map<String, DataEntry>>> getDatas(Response response) throws
                                                                                         NettyWalkthroughException {
        Map<String, Map<String, Map<String, Map<String, DataEntry>>>> body = response.getBody();
        if (body == null) {
            throw new NettyWalkthroughException("Response body is null!");
        }

        Map<String, Map<String, Map<String, DataEntry>>> datas = body.get(dataType.name().toLowerCase());
        if (datas == null) {
            throw new NettyWalkthroughException("Response data is null!");
        }
        return datas;
    }

    private void notify(DataEventType type, Map<String, Map<String, Map<String, DataEntry>>> datas) {
        datas.forEach((service, data0) -> {
            if (data0 != null) {
                data0.forEach((cluster, data) -> {
                    Map<String, Map<DataEventRegistry, Object>> clusterMap = registryMap.get(service);
                    if (clusterMap != null) {
                        Map<DataEventRegistry, Object> registeryList = clusterMap.get(cluster);
                        if (registeryList != null) {
                            registeryList.keySet().forEach((registry) -> {
                                DataEvent event = new DataEvent();
                                event.setType(type);
                                event.setService(service);
                                event.setCluster(cluster);
                                event.setData(data);
                                registry.getDataEventHandler().handle(event);
                            });
                        }
                    }
                });
            }
        });
    }
}
