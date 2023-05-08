package org.function.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.proto.FunctionGrpc;
import org.proto.FunctionOuterClass;

public class GrpcClient {

    /*
        Can be used for testing the server
     */
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("127.0.0.1:8080")
                .usePlaintext()
                .build();

        FunctionGrpc.FunctionBlockingStub stub
                = FunctionGrpc.newBlockingStub(channel);


        try{
            FunctionOuterClass.FunctionResponse helloResponse = stub.invoke(
                    FunctionOuterClass.FunctionRequest.newBuilder()
                            .setText("Client Message")
                            .build());
            System.out.println("Server response: " + helloResponse.getText());
        }catch(Exception e){
            System.out.println(e);
        }


        channel.shutdown();
    }
}
