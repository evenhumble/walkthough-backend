package io.qkits.walkthough.netty.shortpolling.executor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import cn.hutool.json.JSONUtil;
import io.qkits.walkthough.netty.common.NettyWalkthroughContext;
import io.qkits.walkthough.netty.common.exception.NettyWalkthroughException;
import io.qkits.walkthough.netty.shortpolling.exception.DataAlreadyExistException;
import io.qkits.walkthough.netty.shortpolling.exception.DataNotExistException;
import io.qkits.walkthough.netty.shortpolling.response.BasicResponse;
import lombok.RequiredArgsConstructor;

import org.apache.http.HttpHeaders;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;

@RequiredArgsConstructor
public abstract class BasicExecutor<D, R> {

  private final static HttpClientBuilder BUILDER = HttpClients.custom();
  private final static RequestConfig
      CONFIG =
      RequestConfig.custom().setConnectTimeout(2000).setConnectionRequestTimeout(2000)
          .setSocketTimeout(4000).build();
  private final HttpRequestBase request;
  private final TypeReference<BasicResponse<D>> typeReference;
  private final boolean dataNonNull;

  protected R getResult(D data) throws NettyWalkthroughException {
    return null;
  }

  public final R execute() throws NettyWalkthroughException {
    request.setURI(URI.create(NettyWalkthroughContext.getUrl() + request.getURI()));
    request.setHeader(HttpHeaders.AUTHORIZATION, NettyWalkthroughContext.getToken());
    request.setConfig(CONFIG);
    try (CloseableHttpClient httpClient = BUILDER
        .build(); CloseableHttpResponse httpResponse = httpClient.execute(request)) {
      StatusLine status = httpResponse.getStatusLine();
      if (status != null && status.getStatusCode() >= 500) {
        throw new NettyWalkthroughException(
            String.format("HTTP %d: %s", status.getStatusCode(), status.getReasonPhrase()));
      }
      BasicResponse<D> response =
          JSONUtil
              .toBean(EntityUtils.toString(httpResponse.getEntity()), BasicResponse.class, true);
      verifyResponse(response);
      return getResult(response.getData());
    } catch (IOException e) {
      throw new NettyWalkthroughException(e);
    }
  }

  private void verifyResponse(BasicResponse<D> response) throws NettyWalkthroughException {
    if (response == null) {
      throw new NettyWalkthroughException("Response is null!");
    }

    String status = response.getStatus();
    if (status == null) {
      throw new NettyWalkthroughException("Response status is null!");
    }

    switch (status) {
      case "SUCCESS":
        break;
      case "DataExistsError":
        throw new DataAlreadyExistException();
      case "DataNotExistsError":
        throw new DataNotExistException();
      default:
        throw new NettyWalkthroughException(status + ":" + response.getMessage());
    }

    D data = response.getData();
    if (dataNonNull && data == null) {
      throw new NettyWalkthroughException("Response data is null!");
    }
  }

  protected static String urlEncode(String str) throws NettyWalkthroughException {
    try {
      return URLEncoder.encode(String.valueOf(str), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new NettyWalkthroughException(e);
    }
  }
}
