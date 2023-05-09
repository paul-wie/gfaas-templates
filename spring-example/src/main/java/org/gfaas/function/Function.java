package org.gfaas.function;


import org.gfaas.core.adapter.XFaasSpringAdapter;
import org.gfaas.core.model.XFunction;
import org.gfaas.core.model.XRequest;
import org.gfaas.core.model.XResponse;
import org.gfaas.spring.example.HelloController;
import org.gfaas.spring.example.XfaasSpringExampleApplication;

public class Function  extends XFunction {

    @Override
    public XResponse call(XRequest xRequest) {
        /*

         */
        return new XFaasSpringAdapter()
                .invokeRestControllerFunction(
                        XfaasSpringExampleApplication.class,
                        xRequest,
                        HelloController.class,
                        "getWorld");
    }
}
