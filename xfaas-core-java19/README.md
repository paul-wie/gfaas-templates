# XFaaS Core

This project contains different configurations and functionality:
- adapter: Provides a simple integration of spring-boot applications. Functions from spring-controllers can be called easily.
- example: Provides an example function for testing purpose.
- model: Contains the model for sending and receiving http responses and the function model itself.
- runner: Contains all code to server and run the function properly.

## Build

- <code>./gradlew build</code>

## Run Locally

### Jar File

- <code>java -jar ./build/libs/xfaas-0.0.1.jar --functionTarget=org.xfaas.core.example.TestFunction</code>

### Gradle

- <code>./gradlew :runFunction -PfunctionTarget=org.xfaas.core.example.TestFunction</code>


# Adaper

This project also contains a class called XFaasSpringAdapter, which can be used to easily migrate a spring boot project to a function.
One has to do the following steps:

- Copy the Dockerfile from the xfaas-function-java19 project to the target spring project.
- Copy the xfaas-core-0.0.1.jar to your-spring-project/libs
- Add local repo libs to your build.gradle:
<pre>    
repositories {
    mavenCentral()
    flatDir {
        dirs("libs")
    }
}
</pre>
- Add the jar file to your dependencies in build.gradle:

<pre>
implementation name: 'xfaas-core-0.0.1'
</pre>

- Add the following gradle task in your build.gradle:
<pre>
task runFunction(type: JavaExec){
    main = 'org.xfaas.core.runner.XRunner'
    classpath = files('libs/xfaas-core-0.0.1.jar')
    classpath = sourceSets.main.runtimeClasspath
    args = ['--functionTarget=org.xfaas.function.Function']
}
</pre>

- Add the following jar command to your build.gradle:

<pre>
jar {
    from {
        configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) }
    }
    manifest {
        attributes(
                'Main-Class': 'org.xfaas.core.runner.XRunner'
        )
    }
    archiveFileName.set('function.jar')
}
</pre>

- Create your Function file in <code>org.xfaas.function</code> 
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

XFaasSpringAdapter is used to delegate the xRequest to the function <code>getWorld</code> in the RestController named <code>HelloController</code>.
The XFaasSpringAdapter automatically converts the xRequest to <code>@RequestBody</code> and <code>@RequestParam</code>. After that the function <code>getWorld</code> is invoked.
See the project xfaas-spring-example for more information.
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

- deploy with <code>docker build . -t <deployRegistry>/xfaas-spring</code>
- push with <code>docker push deployRegistry>/xfaas-spring</code>
- deploy with <code>xfaas deploy --image deployRegistry>/xfaas-spring <targetPlatformUniqueId></code>
