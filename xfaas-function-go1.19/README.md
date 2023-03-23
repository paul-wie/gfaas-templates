# XFaaS Function Go 1.19

The function is located in <code>function.go</code>.

## Run the function locally

- Set up the IDE to use <code>go 1.19</code> and <code>function.go</code> as entry.

## Run and build the function using docker

- <code>docker build -t xfaas-function-go1.19 . -f FunctionDockerfile</code>
- <code>docker run --rm -p 8080:8080 xfaas-function-go1.19 -f FunctionDockerfile</code>

## Adapter

#### Requirements

You need to have [Docker](https://www.docker.com), otherwise the CLI will not work properly.

#### Adapt your project to XFaaS

1. Execute <code>xfaas adapt --lang go1.19</code> inside the root folder of your go project. The following files are created:
   - <code>./function/function.go</code>
   - <code>./function/README.md</code>
   - <code>FunctionDockerfile</code>
   - <code>function.yaml</code>
2. Execute <code>go get</code> to update the function dependencies. There should be a new entry in you <code>go.mod</code> and <code>go.sum</code> file.
3. Open <code>function.yaml</code> and give your function a name. Also, enter a valid image me and registry, to which the function image should be pushed.
4. Go to your <code>main.go</code> file and call <code>function.RunFuntion()</code> from the <code>main()</code> function.
5. In <code>./function/function.go:Call</code> you can put the code that should be executed on the function call. You can call some code from your existing project.
6. Execute <code>xfaas build function.yaml</code> to build the function locally.
7. Execute <code>xfaas push function.yaml</code> to push the function image to the remote registry.
8. Execute <code>xfaas deploy function.yaml <target_faas_platform></code> to deploy the function to the given FaaS-Platform. 