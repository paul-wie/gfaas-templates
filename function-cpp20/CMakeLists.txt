cmake_minimum_required(VERSION 3.17)
project(xfaas_function_cpp20)

set(CMAKE_CXX_STANDARD 20)

# Add compiler flags
set(CMAKE_CXX_FLAGS -pthread)

set(Sources main.cpp)
set(Headers)
add_executable(xfaas_function_cpp20 ${Sources} ${Headers})

include_directories(xfaas_function_cpp20 xfaas-cpp-httplib)