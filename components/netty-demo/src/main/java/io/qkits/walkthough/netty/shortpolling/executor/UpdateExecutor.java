package io.qkits.walkthough.netty.shortpolling.executor;


import org.apache.http.client.methods.HttpPut;

import com.fasterxml.jackson.core.type.TypeReference;

import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorContext;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;

public class UpdateExecutor extends AppExecutor<Object, Void> {

  public UpdateExecutor(String cluster, String key, String value, DataOperatorContext dataContext)
      throws
      NettyWalkthroughException {
    super(new HttpPut(
              String.format("/%s?key=%s&value=%s", cluster, urlEncode(key), urlEncode(value))),
          new TypeReference<BasicResponse<Object>>() {
          }, false, dataContext);
  }
}
