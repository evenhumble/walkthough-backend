package io.qkits.walkthough.netty.adapter.api.serv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.qkits.walkthough.netty.adapter.api.serv.model.ServiceInstance;
import io.qkits.walkthough.netty.common.DataType;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.api.DataOperator;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorContext;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorFactory;

public class ServiceRegister {
    private final ServiceInstance serviceInstance;
    private final DataOperator dataOperator;

    public ServiceRegister(ServiceInstance serviceInstance) {
        this.serviceInstance = serviceInstance;
        this.dataOperator = DataOperatorFactory
            .newInstance(new DataOperatorContext(DataType.SERVICE, serviceInstance.getName()));
    }

    public void register() throws NettyWalkthroughException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            dataOperator.update(serviceInstance.getCluster(), serviceInstance.getIdentifier(),
                                mapper.writeValueAsString(serviceInstance),
                                mapper.writeValueAsString(serviceInstance.getRuntime()));
        } catch (JsonProcessingException e) {
            throw new NettyWalkthroughException(e);
        }
    }

    public void unregister() throws NettyWalkthroughException {
        dataOperator.delete(serviceInstance.getCluster(), serviceInstance.getIdentifier());
    }
}
