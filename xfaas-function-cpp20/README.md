# XFaaS Function C++20

The function is located in <code>main.cpp</code>.

#### Requirements

You need to have [Docker](https://www.docker.com), otherwise the CLI will not work properly.

## Workflow

##### Build the Function locally and push it to the local docker repository

- <code>xfaas build function.yaml</code>

##### Push the Function to the configured remote repository which can be reached by the FaaS Platform

- <code>xfaas push function.yaml</code>

##### Deploy the Function to the given FaaS Platform

- <code>xfaas deploy function.yaml target_faas_platform</code>

## Run the function locally

Run the following commands to create an executable names <code>xfaas_function_cpp20</code>:

- <code>cmake .</code>
- <code>cmake --build ./</code>
- <code>./xfaas_function_cpp20</code>

You can also setup your IDE to make all these instructions in one step.

## Run and build the function using docker

- <code>docker build -t xfaas-function-cpp20 .</code>
- <code>docker run --rm -p 8080:8080 xfaas-function-cpp20</code>


#### Adapt your project to XFaaS

1. Execute <code>xfaas adapt --lang cpp20</code> inside the root folder of your cpp project. The following files are created:
    - <code>./xfaas-cpp-httplib</code>
    - <code>./README.md</code>
    - <code>./Dockerfile</code>
    - <code>./function.yaml</code>
    - <code>./main.cpp</code>
2. Add <code>include_directories(xfaas-cpp-httplib)</code> to your <code>CMakeLists.txt</code>.
3. Open <code>function.yaml</code> and give your function a name. Also, enter a valid image me and registry, to which the function image should be pushed.
4. In <code>main.cpp</code> you can put your custom logic and can call your code.
6. Follow the steps under **Workflow**