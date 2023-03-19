from flask import make_response
from xfaas_core_python3.xfunction import XFunction
from xfaas_core_python3.xrunner import get_server, run_app


# MyFunctionEntry is used as entry point by the xfaas_core_python3 package as it implements XFunction
class MyFunctionEntry(XFunction):

    def call(self, request):
        # Put your custom logic here
        return make_response(("Hello World: " + str(request.data), 200))


# Required for gunicorn, Set MyFunctionEntry as target_function
app = get_server(MyFunctionEntry())

# Run the server locally
if __name__ == '__main__':
    run_app(MyFunctionEntry())