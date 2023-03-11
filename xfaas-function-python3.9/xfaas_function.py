from flask import Flask, request
from function import handler

app = Flask(__name__)


@app.route("/")
def function_entry():
    return handler.function(request)
