package org.gfaas.core.info;

import org.gfaas.core.model.XFunction;
import org.gfaas.core.model.XResponse;
import org.gfaas.core.model.XRequest;

public class Health extends XFunction {

    @Override
    public XResponse call(XRequest xRequest) {
        var xResponse = new XResponse();

        xResponse.setBody("Ok");
        xResponse.setStatusCode(200);

        return xResponse;
    }
}
