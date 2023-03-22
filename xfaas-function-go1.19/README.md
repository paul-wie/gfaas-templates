# XFaaS Function Go 1.19

The function is located in <code>function.go</code>.

## Run the function locally

- Set up the IDE to use <code>go 1.19</code> and <code>function.go</code> as entry.

## Run and build the function using docker

- <code>docker build -t xfaas-function-go1.19 .</code>
- <code>docker run --rm -p 8080:8080 xfaas-function-go1.19</code>