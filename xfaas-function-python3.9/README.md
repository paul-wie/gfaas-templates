# XFaaS Function Python 3.9

The function is based on [Flask](The function is based on Flask([])
) which is a lightweight web framework.
The business code is located in <code>/function/handler.py</code>. Additional requirements which are used by the function can be placed in <code>requirements.txt</code>.
[Gunicorn](https://gunicorn.org) is used to serve the function in the container/productive environment.

## Run the function locally using flask command

This is useful for testing the function locally. The function can be reached http://127.0.0.1:8080/.

- <code>flask --app xfaas_function run --port 8080</code>

## Run and build the function using docker

- <code>docker build -t xfaas-function-python3.9 .</code>
- <code>docker run --rm -p 8080:8080 xfaas-function-python3.9</code>

