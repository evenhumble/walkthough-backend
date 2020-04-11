package io.qkits.walkthough.netty.shortpolling.executor;


import org.apache.http.client.methods.HttpGet;

import com.fasterxml.jackson.core.type.TypeReference;

import io.qkits.walkthough.netty.common.DataEntry;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.api.DataOperatorContext;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;
import io.qkits.walkthough.netty.shortpolling.response.FullDataEntry;

public class GetExecutor extends AppExecutor<FullDataEntry, DataEntry> {
    public GetExecutor(String cluster, String key, DataOperatorContext dataContext) throws NettyWalkthroughException {
        super(new HttpGet(String.format("/%s?key=%s", cluster, urlEncode(key))), new TypeReference<BasicResponse<FullDataEntry>>() {
        }, true, dataContext);
    }

    @Override
    protected DataEntry getResult(FullDataEntry data) throws NettyWalkthroughException {
        return new DataEntry(data);
    }
}
