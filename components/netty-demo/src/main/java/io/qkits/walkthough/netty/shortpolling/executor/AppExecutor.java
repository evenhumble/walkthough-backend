package io.qkits.walkthough.netty.shortpolling.executor;

import java.net.URI;


import org.apache.http.client.methods.HttpRequestBase;

import com.fasterxml.jackson.core.type.TypeReference;

import io.qkits.walkthough.netty.shortpolling.api.DataOperatorContext;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;

public abstract class AppExecutor<D, R> extends BasicExecutor<D, R> {
    public AppExecutor(HttpRequestBase request, TypeReference<BasicResponse<D>> typeReference, boolean dataNonNull, DataOperatorContext dataContext) {
        super(request, typeReference, dataNonNull);
        request.setURI(URI.create("/" + dataContext + request.getURI()));
    }
}
