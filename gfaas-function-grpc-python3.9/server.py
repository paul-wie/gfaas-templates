from concurrent import futures
import grpc
import function_pb2_grpc
from function import MyFunction
from grpc_reflection.v1alpha import reflection

if __name__ == '__main__':
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    function_pb2_grpc.add_FunctionServicer_to_server(MyFunction(), server)

    SERVICE_NAMES = (
        reflection.SERVICE_NAME,
    )
    reflection.enable_server_reflection(SERVICE_NAMES, server)

    server.add_insecure_port('[::]:8080')
    server.start()
    server.wait_for_termination()

