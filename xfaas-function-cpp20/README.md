# XFaaS Function C++20

The function code is located in <code>function/function.cpp</code>.

## Run the function locally

Run the following commands to create an executable names <code>xfaas_function_cpp20</code>:

- <code>cmake .</code>
- <code>cmake --build ./</code>
- <code>./xfaas_function_cpp20</code>

You can also setup your IDE to make all these instructions in one step.

## Run and build the function using docker

- <code>docker build -t xfaas-function-cpp20 .</code>
- <code>docker run --rm -p 8080:8080 xfaas-function-cpp20</code>