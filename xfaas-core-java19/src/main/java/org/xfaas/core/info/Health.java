package org.xfaas.core.info;

import org.xfaas.core.model.XFunction;
import org.xfaas.core.model.XRequest;
import org.xfaas.core.model.XResponse;

public class Health extends XFunction {

    @Override
    public XResponse call(XRequest xRequest) {
        var xResponse = new XResponse();

        xResponse.setBody("Ok");
        xResponse.setStatusCode(200);

        return xResponse;
    }
}
