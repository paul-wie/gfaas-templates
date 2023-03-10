package org.xfaas.core.model;

import java.util.HashMap;
import java.util.Map;

public class XResponse {

    private String body;
    private Map<String, String> headers;
    private int statusCode;

    public XResponse(){
        this.statusCode = 200;
        this.headers = new HashMap<>();
    }

    public String getBody(){
        return this.body;
    }

    public void setBody(String body){
        this.body = body;
    }

    public Map<String, String> getHeaders(){
        return this.headers;
    }

    public void setHeaders(Map<String, String> headers){
        this.headers = headers;
    }

    public void setHeader(String key, String value){
        this.headers.put(key, value);
    }

    public int getStatusCode(){
        return this.statusCode;
    }

    public void setStatusCode(int statusCode){
        this.statusCode = statusCode;
    }
}
