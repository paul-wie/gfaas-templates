# XFaaS Function

This project contains everything to develop, run and deploy a function written in Java11.
The function is placed in org.xfaas.function.Function and can be extended with the business logic.
The function extends the XFunction class from the xfaas-core project, which provides interfaces, models and the underlying code to server the function.
Do not relocate the Function class, otherwise the build.gradle and the Dockerfile must be adjusted to guarantee proper build and execution.


## Run Function with Gradle

- <code>./ gradlew runFunction</code>

## Build the Function

- <code>./gradlew build</code>

## Run Function from Jar

- <code>java -jar build/libs/function.jar --functionTarget=org.xfaas.function.Function</code>

## Build and Run the Function in a Container
- <code>docker build . -t xfaas-java19-function</code>
- <code>docker run -p 8080:8080 xfaas-java19-function</code>
