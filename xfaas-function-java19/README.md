# XFaaS Function


The function is located in <code>org.xfaas.function.Function</code>.

#### Requirements

You need to have [Docker](https://www.docker.com), otherwise the CLI will not work properly.

## About

This project contains everything to develop, run and deploy a function written in Java11.
The function is placed in org.xfaas.function.Function and can be extended with the business logic.
The function extends the XFunction class from the xfaas-core project, which provides interfaces, models and the underlying code to server the function.
Do not relocate the Function class, otherwise the build.gradle and the Dockerfile must be adjusted to guarantee proper build and execution.

## Workflow

##### Build the Function locally and push it to the local docker repository

- <code>xfaas build function.yaml</code>

##### Push the Function to the configured remote repository which can be reached by the FaaS Platform

- <code>xfaas push function.yaml</code>

##### Deploy the Function to the given FaaS Platform

- <code>xfaas deploy function.yaml target_faas_platform</code>

## Run Function with Gradle

- <code>./ gradlew runFunction</code>

## Build the Function

- <code>./gradlew build</code>

## Run Function from Jar

- <code>java -jar build/libs/function.jar --functionTarget=org.xfaas.function.Function</code>

## Build and Run the Function in a Container
- <code>docker build . -t xfaas-java19-function</code>
- <code>docker run -p 8080:8080 xfaas-java19-function</code>

#### Adapt your project to XFaaS

1. Execute <code>xfaas adapt --lang java19</code> inside the root folder of your java project. The following files are created:
    - <code>./src/main/java/org/xfaas/function/Function.java</code>
    - <code>./libs/xfaas-core-0.0.1.jar</code>
    - <code>./README.md</code>
    - <code>./Dockerfile</code>
    - <code>./function.yaml</code>
2. Add dependency to your <code>build.gradle</code>
3. Add gradle task **runFunction** <code>build.gradle</code>
4. Add proper build configurations to your <code>build.gradle</code>
5. Open <code>function.yaml</code> and give your function a name. Also, enter a valid image me and registry, to which the function image should be pushed.
6. In <code>./org.xfaas.function.Function.java:call</code> you can put the code that should be executed on the function call. You can call some code from your existing project.
7. Follow the steps under **Workflow**

##### Extension for Spring projects

Use the <code>XFaaSSpringAdapter</code> to delegate the request to your Spring <code>@RestController</code> endpoint:

1. Go to the Function <code>org.xfaas.function.Function</code> and user the spring adapter to choose the proper endpoint.
The following example is delegating the request to the <code>getWorld</code> endpoint of the <code>HelloWorldController</code>.

###### Note
The spring <code>@RequestBody</code> and <code>@RequestParam</code> are created by the <code>invokeRestControllerFunction()</code> function of the spring adapter.
**Currently, other endpoint parameters are not provided.**

<pre>
package org.xfaas.function;


import org.xfaas.core.adapter.XFaasSpringAdapter;
import org.xfaas.core.model.XFunction;
import org.xfaas.core.model.XRequest;
import org.xfaas.core.model.XResponse;
import org.xfaas.spring.example.HelloController;
import org.xfaas.spring.example.XfaasSpringExampleApplication;

public class Function extends XFunction {

    @Override
    public XResponse call(XRequest xRequest) {
        return new XFaasSpringAdapter()
            .invokeRestControllerFunction(XfaasSpringExampleApplication.class,
                                          xRequest, 
                                          HelloController.class, 
                                          "getWorld");
    }
}
</pre>

<pre>
package org.xfaas.spring.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public WorldDTO getWorld(@RequestBody WorldDTO worldDTO, @RequestParam String id){
        var result = new WorldDTO();
        result.world = "World: " + worldDTO.world + ", ID: " + id;
        return result;
    }
}
</pre>
