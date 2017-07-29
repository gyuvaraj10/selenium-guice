package com.app.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Yuvaraj on 27/07/2017.
 */
public class WebServiceClient {

    private static HttpClient httpClient;
    private HttpEntity httpEntity;
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private static WebServiceClient webServiceClient;
    private static HttpHost httpHost;
    private Map<String, String> headers;
    private RequestConfig requestConfig;

    private WebServiceClient() {
        headers = new HashMap<>();
    }

    public static WebServiceClient build() {
        httpClient = HttpClientBuilder.create().build();
        return getWebServiceClient();
    }

    public static WebServiceClient build(String hostName, int port, String schema) {
        httpClient = HttpClientBuilder.create().build();
        httpHost = new HttpHost(hostName, port, schema);
        return getWebServiceClient();
    }

    public WebServiceClient setSocketAndConnectTimeOut(int socketTimeOut, int connectTimeOut) {
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeOut)
                .setConnectTimeout(connectTimeOut)
                .build();
        return getWebServiceClient();
    }

    public WebServiceClient withHeader(String key, String value) {
        headers.put(key, value);
        return getWebServiceClient();
    }

    public WebServiceClient withHeaders(Map<String, String> headers) {
        headers.putAll(headers);
        return getWebServiceClient();
    }

    public WebServiceClient withRequestBody(String requestBody, ContentType contentType) {
        httpEntity = new StringEntity(requestBody, contentType);
        return getWebServiceClient();
    }

    public WebServiceClient withRequest(String url, String method) {
        switch (method.toUpperCase()) {
            case "GET": {
                httpRequest = new HttpGet(url);
                ((HttpGet)httpRequest).setConfig(requestConfig);
                break;
            }
            case "POST": {
                httpRequest = new HttpPost(url);
                ((HttpPost)httpRequest).setEntity(httpEntity);
                ((HttpPost)httpRequest).setConfig(requestConfig);
                break;
            }
            case "PATCH": {
                httpRequest = new HttpPatch(url);
                ((HttpPatch)httpRequest).setEntity(httpEntity);
                ((HttpPatch)httpRequest).setConfig(requestConfig);
                break;
            }
            case "PUT": {
                httpRequest = new HttpPut(url);
                ((HttpPut)httpRequest).setEntity(httpEntity);
                ((HttpPut)httpRequest).setConfig(requestConfig);
                break;
            }
            case "OPTIONS": {
                httpRequest = new HttpOptions(url);
                ((HttpOptions)httpRequest).setConfig(requestConfig);
                break;
            }
            case "HEAD": {
                httpRequest = new HttpHead(url);
                ((HttpHead)httpRequest).setConfig(requestConfig);
                break;
            }
            case "TRACE": {
                httpRequest = new HttpTrace(url);
                ((HttpTrace)httpRequest).setConfig(requestConfig);
                break;
            }
            case "DELETE": {
                httpRequest = new HttpDelete(url);
                ((HttpDelete)httpRequest).setConfig(requestConfig);
                break;
            }
            default: {
                httpRequest = new HttpGet(url);
                ((HttpGet)httpRequest).setConfig(requestConfig);
                break;
            }
        }
        return getWebServiceClient();
    }

    public WebServiceClient sendAndReceive() throws Exception{
        // response handler is added here so that user is relieved form worrying about connection management
        // When using a ResponseHandler, HttpClient will automatically take care of ensuring release of
        // the connection back to connection manager regardless whether the execution is succeeds or causes exception
        ResponseHandler<HttpResponse> responseHandler = new ResponseHandler<HttpResponse>() {
            @Override
            public HttpResponse handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
               return httpResponse;
            }
        };
        if(httpHost == null) {
            httpResponse = httpClient.execute((HttpUriRequest) httpRequest, responseHandler);
        } else {
            httpResponse  = httpClient.execute(httpHost, httpRequest, responseHandler);
        }
        return getWebServiceClient();
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public String getResponseBody() throws IOException{
        return IOUtils.toString(httpResponse.getEntity().getContent(), Charset.defaultCharset());
    }

    public Map<String, String> getResponseHeaders() throws IOException{
        return Arrays.stream(httpResponse.getAllHeaders())
                .flatMap(x->Arrays.stream(x.getElements()))
                .collect(Collectors.toMap(HeaderElement::getName, HeaderElement::getValue));
    }

    private static WebServiceClient getWebServiceClient() {
        if(webServiceClient == null) {
            webServiceClient = new WebServiceClient();
        }
        return webServiceClient;
    }
}
