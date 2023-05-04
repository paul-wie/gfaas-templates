package org.function.server;

import io.grpc.*;
import org.function.service.FunctionEndpoint;


import io.grpc.protobuf.services.ProtoReflectionService;

public class GrpcServer {
    public static void main(String[] args) throws Exception{

        Server server = ServerBuilder.forPort(8080)
                .addService(new FunctionEndpoint())
                .addService(ProtoReflectionService.newInstance())
                .build();
        server.start();
        server.awaitTermination();
    }
}


