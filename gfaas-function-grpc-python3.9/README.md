# gFaaS Function

#### Requirements

You need to have [Docker](https://www.docker.com), otherwise the CLI will not work properly.

## About

This project allows you to:
- Define a service in .proto notation
- Generate server and client code using the protocol buffer compiler
- Use Python grpc API to write server and client for your service
- Build and deploy your service to Knative using the gFaaS Cli

#### Code

- Proto file in ```function.proto```
- Service implementation in ```function.py```
- Server implementation in ```server.py```
- Client implementation in ```client.py```

#### Development

Execute

```
python -m grpc_tools.protoc -I. --python_out=. --pyi_out=. --grpc_python_out=. function.proto
```

to generate stub files from the ```function.proto``` file which can then be implemented in ```function.py```, ```server.py``` and ```cliet.py```.

#### [grpcurl](https://github.com/fullstorydev/grpcurl)


```
grpcurl -plaintext 127.0.0.1:8080 org.proto.Function/invoke
```

returns 
<pre>
{
  "text": "Response from server"
}
</pre>

## Workflow

##### Build the Function locally and push it to the local docker repository

```
pip3 freeze > requirements.txt
```

```
gfaas build function.yaml
```

##### Push the Function to the configured remote repository which can be reached by the FaaS Platform

```
gfaas push function.yaml
```

##### Deploy the Function to the given FaaS Platform

```
gfaas deploy function.yaml target_faas_platform
```

## Build and Run the Function in a Container
```
docker build . -t gfaas-function-grpc-java19
```
```
docker run -p 8080:8080 gfaas-function-grpc-java19
```

