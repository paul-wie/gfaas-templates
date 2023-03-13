# Get the base Ubuntu image from Docker Hub
FROM ubuntu:latest

# Update apps on the base image
RUN apt-get -y update && apt-get install -y
RUN apt-get install cmake cmake-qt-gui -y

# Install the Clang compiler
RUN apt-get -y install clang

# Copy the current folder which contains C++ source code to the Docker image under /usr/src
COPY . /usr/src/xfaas

# Specify the working directory
WORKDIR /usr/src/xfaas

# Use cmake to build the function
RUN cmake .
RUN cmake --build ./

# Run the output program from the previous step
EXPOSE 8080
CMD ["./xfaas_function_cpp20"]