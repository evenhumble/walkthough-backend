package io.qkits.walkthough.netty.shortpolling.executor;


import org.apache.http.client.methods.HttpPost;

import com.fasterxml.jackson.core.type.TypeReference;

import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorContext;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;

public class CreateExecutor extends AppExecutor<Object, Void> {

  public CreateExecutor(String cluster, String key, String value, String runtime,
                        DataOperatorContext dataContext) throws
                                                         NettyWalkthroughException {
    super(new HttpPost(String.format("/%s?key=%s&value=%s&runtime=%s", cluster, urlEncode(key),
                                     urlEncode(value), urlEncode(runtime))),
          new TypeReference<BasicResponse<Object>>() {
          }, false, dataContext);
  }
}
