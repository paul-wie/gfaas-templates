package org.xfaas.core.example;

import org.xfaas.core.model.XFunction;
import org.xfaas.core.model.XRequest;
import org.xfaas.core.model.XResponse;

public class TestFunction extends XFunction {

    @Override
    public XResponse call(XRequest xRequest) {
        var xResponse = new XResponse();

        xResponse.setBody("Hello from xFaas: " + xRequest.getBodyString());
        xResponse.setHeader("xFaasHeader", "xFaasValue");
        xResponse.setStatusCode(200);

        return xResponse;
    }
}
