import grpc

import function_pb2
import function_pb2_grpc

if __name__ == '__main__':
    channel = grpc.insecure_channel('localhost:8080')
    stub = function_pb2_grpc.FunctionStub(channel)

    request = function_pb2.FunctionRequest(text='Hallo Welt')

    response = stub.invoke(request)

    print(response)

