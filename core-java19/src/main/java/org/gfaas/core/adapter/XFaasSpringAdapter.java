package org.gfaas.core.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gfaas.core.model.XResponse;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.gfaas.core.model.XRequest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class XFaasSpringAdapter {

    public XResponse invokeRestControllerFunction(Class application, XRequest xRequest, Class springBean, String functionName){
        var context =
                new SpringApplicationBuilder(application)
                        .web(WebApplicationType.NONE)
                        .run();

        var controller = context.getBean(springBean);
        var xResponse = new XResponse();
        ObjectMapper objectMapper = new ObjectMapper();


        var targetMethod = Arrays.stream(controller.getClass().getMethods()).filter(m -> m.getName().equals(functionName)).findAny();
        if(targetMethod.isEmpty()){
            xResponse.setStatusCode(500);
            xResponse.setBody("Could not find method " + functionName + " in bean " + springBean + ".");
            return xResponse;
        }

        if(isFunctionParameterUnannotated(targetMethod.get())){
            xResponse.setStatusCode(500);
            xResponse.setBody("There are function parameters without annotations. Only RequestParam and RequestBody allowed.");
            return xResponse;
        }

        Object[] params = new Object[targetMethod.get().getParameterCount()];
        int paramsIndex = 0;
        for(var m : targetMethod.get().getParameterAnnotations()){
            if(m[0].annotationType().getName().equals("org.springframework.web.bind.annotation.RequestBody")){

                var clazz = targetMethod.get().getParameters()[paramsIndex].getParameterizedType().getTypeName();

                Object dto;
                Class requestBodyClass = null;
                try {
                    requestBodyClass = Class.forName(clazz);
                    dto = objectMapper.readValue(xRequest.body, requestBodyClass);
                }catch (Exception io){
                    xResponse.setStatusCode(400);
                    var receivedBody = xRequest.getBodyString();
                    var expectedBody = requestBodyClass != null ? Arrays.stream(requestBodyClass.getFields()).map(f -> f.getName()).toList() : new ArrayList();
                    String expectedBodyJoined = String.join(",", expectedBody);
                    xResponse.setBody("Expected json with members: " + expectedBodyJoined + ".\nBut received:\n" + receivedBody);
                    return xResponse;
                }
                params[paramsIndex] = dto;
            }else if(m[0].annotationType().getName().equals("org.springframework.web.bind.annotation.RequestParam")){
                var paramName = targetMethod.get().getParameters()[paramsIndex].getName();
                var xfaasParam = xRequest.queryParameters.get(paramName);
                if(xfaasParam == null){
                    xResponse.setBody("Expected parameter " + paramName + " was not provided.");
                    xResponse.setStatusCode(500);
                    return xResponse;
                }
                params[paramsIndex] = xfaasParam;
            }
            paramsIndex++;
        }


        Object result;
        try{
            result = targetMethod.get().invoke(controller, params);
        }catch(Exception e){
            xResponse.setBody("Could not invoke function");
            xResponse.setStatusCode(500);
            return xResponse;
        }


        try{
            xResponse.setBody(objectMapper.writeValueAsString(result));
            xResponse.setStatusCode(200);
            return xResponse;
        }catch (Exception e){
            xResponse.setBody("Could not convert result to json");
            xResponse.setStatusCode(500);
            return xResponse;
        }
    }

    private boolean isFunctionParameterUnannotated(Method m){

        var requestBody = "org.springframework.web.bind.annotation.RequestBody";
        var requestParam = "org.springframework.web.bind.annotation.RequestParam";

        for(var annotation : m.getParameterAnnotations()){
            if(annotation.length == 0){
                return true;
            }else if(annotation[0].annotationType().getName().equals(requestBody) || annotation[0].annotationType().getName().equals(requestParam)){
                continue;
            }else{
                return true;
            }
        }
        return false;
    }

}
