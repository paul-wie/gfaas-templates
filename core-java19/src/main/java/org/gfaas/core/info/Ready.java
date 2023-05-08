package org.gfaas.core.info;

import org.gfaas.core.model.XFunction;
import org.gfaas.core.model.XRequest;
import org.gfaas.core.model.XResponse;

public class Ready extends XFunction {

    @Override
    public XResponse call(XRequest xRequest) {
        var xResponse = new XResponse();

        xResponse.setBody("Ok");
        xResponse.setStatusCode(200);

        return xResponse;
    }
}
