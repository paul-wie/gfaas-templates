import function_pb2
import function_pb2_grpc


class MyFunction(function_pb2_grpc.Function):
    def invoke(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return function_pb2.FunctionResponse(text='Response from server')