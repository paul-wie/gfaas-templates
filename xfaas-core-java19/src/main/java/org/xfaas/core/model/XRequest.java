package org.xfaas.core.model;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class XRequest {

    public byte[] body;
    public Map<String, String> headers;
    public Map<String, String> queryParameters;
    public String requestMethod;

    public XRequest(){
        this.headers = new HashMap<>();
        this.queryParameters = new HashMap<>();
    }

    public String getBodyString(){
        return new String(body, StandardCharsets.UTF_8);
    }

}
