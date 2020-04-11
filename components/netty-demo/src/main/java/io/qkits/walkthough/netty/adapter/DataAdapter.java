package io.qkits.walkthough.netty.adapter;

import java.util.HashMap;
import java.util.Map;

import io.qkits.walkthough.netty.common.DataType;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.longpolling.api.DataEventRegistry;
import io.qkits.walkthough.netty.longpolling.api.DataFetcher;
import io.qkits.walkthough.netty.longpolling.api.DataFetcherContext;
import io.qkits.walkthough.netty.longpolling.api.DataFetcherFactory;


public class DataAdapter<T> {
    private final DataAdapterContext<T> dataContext;
    private final DataFetcher dataFetcher;
    private final Map<String, DataIncidentEntry<T>> entries = new HashMap<>();

    public DataAdapter(DataType dataType, DataParser<T> dataParser) {
        this.dataContext = new DataAdapterContext<>(dataType, dataParser);
        this.dataFetcher = DataFetcherFactory.newInstance(new DataFetcherContext(dataType));
    }

    public void register(DataIncidentRegistry<T> registry) throws NettyWalkthroughException {
        prepareEntry(registry.getService(), registry.getCluster()).register(registry.getDataIncidentHandler());
    }

    protected synchronized DataIncidentEntry<T> prepareEntry(String service, String cluster) throws NettyWalkthroughException {
        String key = String.format("%s@%s", service, cluster);
        DataIncidentEntry<T> entry = entries.get(key);
        if (entry == null) {
            entry = new DataIncidentEntry<>(dataContext, service, cluster);
            dataFetcher.register(new DataEventRegistry(entry, service, cluster));
            entries.put(key, entry);
        }
        return entry;
    }

    public void stop() {
        dataFetcher.stop();
    }
}
