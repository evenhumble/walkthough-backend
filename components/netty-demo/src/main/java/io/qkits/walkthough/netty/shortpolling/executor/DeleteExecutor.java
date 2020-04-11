package io.qkits.walkthough.netty.shortpolling.executor;


import org.apache.http.client.methods.HttpDelete;

import com.fasterxml.jackson.core.type.TypeReference;

import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorContext;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;

public class DeleteExecutor extends AppExecutor<Object, Void> {
    public DeleteExecutor(String cluster, String key, DataOperatorContext dataContext) throws
                                                                                       NettyWalkthroughException {
        super(new HttpDelete(String.format("/%s?key=%s", cluster, urlEncode(key))), new TypeReference<BasicResponse<Object>>() {
        }, false, dataContext);
    }
}
