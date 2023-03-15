from flask import Flask, request
from function import handler

app = Flask(__name__)


@app.route("/", methods=['GET', 'POST'])
def function_entry():
    return handler.function(request)


# Required for OpenFaaS health check
@app.route("/_/health")
def health():
    return app.make_response(("Ok", 200))


# Required for OpenFaaS readiness check
@app.route("/_/ready")
def ready():
    return app.make_response(("Ok", 200))


# Local testing
if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=8080)
