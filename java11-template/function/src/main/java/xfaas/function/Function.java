package xfaas.function;

import xfaas.config.XRequest;
import xfaas.config.XResponse;

import java.nio.charset.StandardCharsets;


public class Function {

    public XResponse call(XRequest xRequest) throws Exception{
        var response = new XResponse();

        response.setStatusCode(200);
        response.setBody("Hello " + new String(xRequest.body, StandardCharsets.UTF_8) + " (From xfaas).");

        response.setHeader("xfaasheader", "HelloWorld");

        return response;
    }

}
