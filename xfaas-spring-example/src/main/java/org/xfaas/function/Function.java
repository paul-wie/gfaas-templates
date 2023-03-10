package org.xfaas.function;


import org.xfaas.core.adapter.XFaasSpringAdapter;
import org.xfaas.core.model.XFunction;
import org.xfaas.core.model.XRequest;
import org.xfaas.core.model.XResponse;
import org.xfaas.spring.example.HelloController;
import org.xfaas.spring.example.XfaasSpringExampleApplication;

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
