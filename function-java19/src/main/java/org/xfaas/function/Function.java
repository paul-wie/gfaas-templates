package org.xfaas.function;

import org.gfaas.core.model.XFunction;
import org.gfaas.core.model.XRequest;
import org.gfaas.core.model.XResponse;

public class Function extends XFunction {

    @Override
    public XResponse call(XRequest xRequest) {

        var xResponse = new XResponse();

        xResponse.setBody("Hello: " + xRequest.getBodyString());
        xResponse.setHeader("MyCustomHeader", "MyCustomValue");
        xResponse.setStatusCode(200);

        return xResponse;
    }
}
