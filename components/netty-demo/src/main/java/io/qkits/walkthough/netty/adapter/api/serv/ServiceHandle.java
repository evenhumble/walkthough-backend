package io.qkits.walkthough.netty.adapter.api.serv;


import com.fasterxml.jackson.databind.ObjectMapper;

import io.qkits.walkthough.netty.adapter.DataAdapter;
import io.qkits.walkthough.netty.adapter.api.serv.model.ServiceInstance;
import io.qkits.walkthough.netty.adapter.api.serv.model.ServiceRuntime;
import io.qkits.walkthough.netty.common.DataType;

public class ServiceHandle extends DataAdapter<ServiceInstance> {

  public ServiceHandle() {
    super(DataType.SERVICE, (data) -> {
      if (data == null) {
        return null;
      } else {
        ServiceInstance
            serviceInstance =
            new ObjectMapper().readValue(data.getValue(), ServiceInstance.class);
        ServiceRuntime runtime;
        if (data.getRuntime() == null || data.getRuntime().isEmpty()) {
          runtime = new ServiceRuntime();
        } else {
          runtime = new ObjectMapper().readValue(data.getRuntime(), ServiceRuntime.class);
        }
        serviceInstance.setRuntime(runtime);
        return serviceInstance;
      }
    });
  }
}
