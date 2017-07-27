package com.app.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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

    private WebServiceClient() {
        headers = new HashMap<>();
        httpClient = HttpClientBuilder.create().build();
    }

    public static WebServiceClient build() {
        return getWebServiceClient();
    }

    public static WebServiceClient build(String hostName, int port, String schema) {
        httpHost = new HttpHost(hostName, port, schema);
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
                break;
            }
            case "POST": {
                httpRequest = new HttpPost(url);
                ((HttpPost)httpRequest).setEntity(httpEntity);
                break;
            }
            case "PATCH": {
                httpRequest = new HttpPatch(url);
                ((HttpPatch)httpRequest).setEntity(httpEntity);
                break;
            }
            case "PUT": {
                httpRequest = new HttpPut(url);
                ((HttpPut)httpRequest).setEntity(httpEntity);
                break;
            }
            case "OPTIONS": {
                httpRequest = new HttpOptions(url);
                break;
            }
            case "HEAD": {
                httpRequest = new HttpHead(url);
                break;
            }
            case "TRACE": {
                httpRequest = new HttpTrace(url);
                break;
            }
            case "DELETE": {
                httpRequest = new HttpDelete(url);
                break;
            }
            default: {
                httpRequest = new HttpGet(url);
                break;
            }
        }
        return getWebServiceClient();
    }

    public WebServiceClient sendAndReceive() throws Exception{
        if(httpHost == null) {
            httpResponse = httpClient.execute((HttpUriRequest) httpRequest);
        } else {
            httpResponse  = httpClient.execute(httpHost, httpRequest);
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
