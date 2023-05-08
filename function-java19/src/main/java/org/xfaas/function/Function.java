package org.xfaas.function;

import org.xfaas.core.model.XFunction;
import org.xfaas.core.model.XRequest;
import org.xfaas.core.model.XResponse;

public class Function extends XFunction {

    @Override
    public XResponse call(XRequest xRequest) {

        var xResponse = new XResponse();

        xResponse.setBody("My first XFaaS Function: " + xRequest.getBodyString());
        xResponse.setHeader("MyXFaasHeader", "MyXFaasValue");
        xResponse.setStatusCode(200);

        return xResponse;
    }
}
