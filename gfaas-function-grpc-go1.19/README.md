# gFaaS Function

#### Requirements

You need to have [Docker](https://www.docker.com), otherwise the CLI will not work properly.

## About

This project allows you to:
- Define a service in .proto notation
- Run Server and Client code which uses the function.proto definition

#### Code

- Proto file in ```function.proto```
- Server implementation in ```server.go```
- Client implementation in ```client.go```
- Function implementation in ```function.go```

#### Development

- Generate stub files ```function.pb.go``` and ```function_grpc.pb.go``` using the command 

```
PATH="${PATH}:${HOME}/go/bin" protoc --go_out=. --go-grpc_out=. function.proto
```
- Run server in ```server.go```

The code also contains the ```google.golang.org/grpc/reflection``` package which allows to request the API definition.

#### [grpcurl](https://github.com/fullstorydev/grpcurl)


```
grpcurl -plaintext 127.0.0.1:8080 org.proto.Function/invoke
```


## Workflow

##### Build the Function locally and push it to the local docker repository

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
docker build . -t gfaas-function-grpc-go119
```
```
docker run -p 8080:8080 gfaas-function-grpc-go119
```

