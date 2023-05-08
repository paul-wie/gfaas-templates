# gFaaS Core

This project contains different configurations and functionality:
- adapter: Provides a simple integration of spring-boot applications. Functions from spring-controllers can be called easily.
- example: Provides an example function for testing purpose.
- model: Contains the model for sending and receiving http responses and the function model itself.
- runner: Contains all code to server and run the function properly.

## Build

- <code>./gradlew build</code>

### Distribution

Currently, the jar file from the build section in <code>build/libs/gfaas-core-0.0.1.jar</code> is distributed manually. That means the jar file is copied to other projects and then included. 
In the future, the jar file should be published on a public repository like [Maven Central](https://mvnrepository.com/repos/central) to make updates more convenient.

## Run Locally

### Jar File

- <code>java -jar ./build/libs/gfaas-0.0.1.jar --functionTarget=TestFunction</code>

### Gradle

- <code>./gradlew :runFunction -PfunctionTarget=org.gfaas.core.example.TestFunction</code>


# Adaper

This project also contains a class called gfaasSpringAdapter, which can be used to easily migrate a spring boot project to a function.
One has to do the following steps:

- Copy the Dockerfile from the gfaas-function-java19 project to the target spring project.
- Copy the gfaas-core-0.0.1.jar to your-spring-project/libs
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
implementation name: 'gfaas-core-0.0.1'
</pre>

- Add the following gradle task in your build.gradle:
<pre>
task runFunction(type: JavaExec){
    main = 'XRunner'
    classpath = files('libs/gfaas-core-0.0.1.jar')
    classpath = sourceSets.main.runtimeClasspath
    args = ['--functionTarget=org.gfaas.function.Function']
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
                'Main-Class': 'XRunner'
        )
    }
    archiveFileName.set('function.jar')
}
</pre>

- Create your Function file in <code>org.gfaas.function</code> 
<pre>
package org.gfaas.function;


import gfaasSpringAdapter;
import XFunction;
import XRequest;
import XResponse;
import org.gfaas.spring.example.HelloController;
import org.gfaas.spring.example.gfaasSpringExampleApplication;

public class Function extends XFunction {

    @Override
    public XResponse call(XRequest xRequest) {
        return new gfaasSpringAdapter()
            .invokeRestControllerFunction(gfaasSpringExampleApplication.class,
                                          xRequest, 
                                          HelloController.class, 
                                          "getWorld");
    }
}
</pre>

gfaasSpringAdapter is used to delegate the xRequest to the function <code>getWorld</code> in the RestController named <code>HelloController</code>.
The gfaasSpringAdapter automatically converts the xRequest to <code>@RequestBody</code> and <code>@RequestParam</code>. After that the function <code>getWorld</code> is invoked.
See the project gfaas-spring-example for more information.
<pre>
package org.gfaas.spring.example;

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

- deploy with <code>docker build . -t <deployRegistry>/gfaas-spring</code>
- push with <code>docker push deployRegistry>/gfaas-spring</code>
- deploy with <code>gfaas deploy --image deployRegistry>/gfaas-spring <targetPlatformUniqueId></code>
