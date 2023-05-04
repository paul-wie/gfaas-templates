package org.function.service;

import io.grpc.stub.StreamObserver;
import org.proto.FunctionGrpc;
import org.proto.FunctionOuterClass;

public class FunctionEndpoint extends FunctionGrpc.FunctionImplBase{

    @Override
    public void invoke(FunctionOuterClass.FunctionRequest request, StreamObserver<FunctionOuterClass.FunctionResponse> responseObserver) {
        // Build a new response
        FunctionOuterClass.FunctionResponse response = FunctionOuterClass.FunctionResponse.newBuilder()
                .setText("Hello from server: " + request.getText())
                .build();

        // Send the response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
