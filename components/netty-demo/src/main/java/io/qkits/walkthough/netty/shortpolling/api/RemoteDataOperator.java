package io.qkits.walkthough.netty.shortpolling.api;

import java.util.List;
import java.util.Map;

import io.qkits.walkthough.netty.common.DataEntry;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.exception.DataAlreadyExistException;
import io.qkits.walkthough.netty.shortpolling.exception.DataNotExistException;
import io.qkits.walkthough.netty.shortpolling.executor.CreateExecutor;
import io.qkits.walkthough.netty.shortpolling.executor.DeleteExecutor;
import io.qkits.walkthough.netty.shortpolling.executor.GetClustersExecutor;
import io.qkits.walkthough.netty.shortpolling.executor.GetExecutor;
import io.qkits.walkthough.netty.shortpolling.executor.GetMapExecutor;
import io.qkits.walkthough.netty.shortpolling.executor.UpdateExecutor;
import lombok.Data;

@Data
public class RemoteDataOperator implements DataOperator {
    private final DataOperatorContext dataContext;

    @Override
    public void update(String cluster, String key, String value, String runtime) throws NettyWalkthroughException {
        try {
            new CreateExecutor(cluster, key, value, runtime, dataContext).execute();
        } catch (DataAlreadyExistException e) {
            new UpdateExecutor(cluster, key, value, dataContext).execute();
        }
    }

    @Override
    public void delete(String cluster, String key) throws NettyWalkthroughException {
        try {
            new DeleteExecutor(cluster, key, dataContext).execute();
        } catch (DataNotExistException e) {
        }
    }

    @Override
    public DataEntry get(String cluster, String key) throws NettyWalkthroughException {
        try {
            return new GetExecutor(cluster, key, dataContext).execute();
        } catch (DataNotExistException e) {
            return null;
        }
    }

    @Override
    public Map<String, DataEntry> getMap(String cluster) throws NettyWalkthroughException {
        return new GetMapExecutor(cluster, dataContext).execute();
    }

    @Override
    public List<String> getClusters() throws NettyWalkthroughException {
        return new GetClustersExecutor(dataContext).execute();
    }
}
